package com.as.erp.trade.micro.container.controller;

import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.container.entity.Container;
import com.as.erp.trade.micro.container.entity.ContainerProductItem;
import com.as.erp.trade.micro.container.entity.ContainerSheet;
import com.as.erp.trade.micro.container.entity.PreloadedProductItem;
import com.as.erp.trade.micro.container.service.ContainerProductItemService;
import com.as.erp.trade.micro.container.service.ContainerService;
import com.as.erp.trade.micro.container.service.ContainerSheetService;
import com.as.erp.trade.micro.container.service.PreloadedProductItemService;
import com.as.erp.trade.micro.container.vo.ContainerAccumulativeTotal;
import com.as.erp.trade.micro.order.entity.FavorCustomer;
import com.as.erp.trade.micro.order.entity.OrderProductItem;
import com.as.erp.trade.micro.order.service.FavorCustomerService;
import com.as.erp.trade.micro.order.service.OrderProductItemService;
import com.as.erp.trade.micro.system.service.UserConfigItemService;
import com.as.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 16-4-26.
 */
@Controller
public class ContainerController {
    public final static String DEFAULT_CONTAINER_SHEET = "default_container_sheet";
    public final static String DEFAULT_CONTAINER = "default_container";

    @Autowired
    private ContainerService containerService;
    @Autowired
    private FavorCustomerService favorCustomerService;
    @Autowired
    private OrderProductItemService orderProductItemService;
    @Autowired
    private ContainerProductItemService containerProductItemService;
    @Autowired
    private UserConfigItemService userConfigItemService;
    @Autowired
    private ContainerSheetService containerSheetService;
    @Autowired
    private PreloadedProductItemService preloadedProductItemService;

    @RequestMapping("container/containerSheet")
    public String containerSheet(
            @RequestParam(required = false) String sheetId,
            @RequestParam(required = false) String containerId,
            ModelMap modelMap,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        if (sheetId == null) {
            sheetId = userConfigItemService.getValue(user, DEFAULT_CONTAINER_SHEET);
        } else {
            String oldSheetId = userConfigItemService.getValue(user, DEFAULT_CONTAINER_SHEET);
            // TO-DO 自动存档

            userConfigItemService.setValue(user, DEFAULT_CONTAINER_SHEET, sheetId);
        }

        if (sheetId != null) {
            if (StringUtils.isNotBlank(sheetId)) {
                ContainerSheet sheet = containerSheetService.getById(sheetId);
                if (sheet != null) {
                    modelMap.put("sheet", sheet);
                    FavorCustomer favorCustomer = favorCustomerService.getById(sheet.getFavorCustomerId());
                    modelMap.put("favorCustomer", favorCustomer);
                }

                List<Container> containerList = containerService.getList(
                        new Query().setPageSize(Integer.MAX_VALUE)
                                .addOrder(Order.asc("indexNumber"))
                                .setConditions(
                                        Conditions.newInstance().eq("sheetId", sheetId)
                                )
                );
                modelMap.put("containerList", containerList);

                if (!containerList.isEmpty()) {

                    if (containerId == null) {
                        containerId = userConfigItemService.getValue(user, DEFAULT_CONTAINER);
                    }
                    Container container = null;
                    if (containerId != null) {
                        container = containerService.getById(containerId);
                        if (container != null) {
                            ContainerSheet targetSheet = containerSheetService.getById(container.getSheetId());
                            if (targetSheet != null) {
                                if (!targetSheet.getId().equals(sheetId)) {
                                    containerId = null;
                                }
                            }
                        } else {
                            containerId = null;
                        }
                        userConfigItemService.setValue(user, DEFAULT_CONTAINER, containerId);
                    }

                    if (containerId == null) {
                        containerId = containerList.get(0).getId();
                        container = containerList.get(0);
                    }

                    List<ContainerProductItem> containerProductItemList = containerProductItemService.getList(
                            new Query().setPageSize(Integer.MAX_VALUE)
                                    .addOrder(Order.desc("createdTime"))
                                    .setConditions(
                                            Conditions.newInstance().eq("containerId", containerId)
                                    )
                    );
                    modelMap.put("container", container);
                    modelMap.put("containerProductItemList", containerProductItemList);
                }


            }
        }
        return "container/containerForm";
    }

    private int getAvailableIndexNumberOfToday(int index, Set<Integer> usedIndexSet) {
        while (true) {
            if (usedIndexSet.contains(index)) {
                index++;
            } else {
                usedIndexSet.add(index);
                return index;
            }
        }
    }

    @RequestMapping("container/generateFromOrder")
    public String generateFromOrder(
            @RequestParam String favorId,
            @RequestParam Double containerVolume,
            ModelMap modelMap
    ) throws InterruptedException {
        Calendar cNow = Calendar.getInstance();
        cNow.setTime(new Date());
        long endTime = cNow.getTimeInMillis();

        cNow.set(cNow.get(Calendar.YEAR), cNow.get(Calendar.MONTH), cNow.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        long startTime = cNow.getTimeInMillis();

        ContainerSheet sheetOfToday = null;
        if (StringUtils.isNotBlank(favorId)) {
            FavorCustomer favorCustomer = favorCustomerService.getById(favorId);
            sheetOfToday = containerSheetService.get(
                    Conditions.newInstance().eq("favorCustomerId", favorId)
                            .between("createdTime", startTime, endTime)
            );

            Set<Integer> usedIndexSet = new HashSet<>();
            if (sheetOfToday == null) {
                sheetOfToday = new ContainerSheet();
                sheetOfToday.setFavorCustomerId(favorId);
                sheetOfToday.setCustomerName(favorCustomer.getCustomerName());
                sheetOfToday.setCurrency(favorCustomer.getCurrency());
                containerSheetService.save(sheetOfToday);
            } else {
                List<Container> todayContainerList = containerService.getList(
                        Conditions.newInstance().eq("sheetId", sheetOfToday.getId())
                );
                for (Container container : todayContainerList) {
                    usedIndexSet.add(container.getIndexNumber());
                }
            }


            List<OrderProductItem> itemList = orderProductItemService.getListByFavor(favorId);

            Container container = null;
            Double containedVolume = 0D;
            for (Object oItem : itemList) {
                OrderProductItem item = (OrderProductItem) oItem;
                if (item.getScheduledDeliverableCartonQuantity() == null || item.getScheduledDeliverableCartonQuantity() <= 0 ) {
                    continue;
                }

                containedVolume += item.getVolume();

                if (containedVolume > containerVolume) {
                    containedVolume = item.getVolume();
                    container = null;
                }

                if (container == null) {
                    container = new Container();
                    container.setContainerVolume(containerVolume);
                    container.setSheetId(sheetOfToday.getId());
                    container.setIndexNumber(getAvailableIndexNumberOfToday(1, usedIndexSet));
                    containerService.save(container);
                }

                ContainerProductItem cpItem = new ContainerProductItem();
                cpItem.setContainerId(container.getId());
                cpItem.setImageURL(item.getImageURL());
                cpItem.setCompanyProductName(item.getCompanyProductName());
                cpItem.setCompanyProductNo(item.getCompanyProductNo());
                cpItem.setPackageForm(item.getPackageForm());
                cpItem.setCartonSize(item.getCartonSize());
                cpItem.setPackingQuantity(item.getPackingQuantity());
                cpItem.setGrossWeight(item.getGrossWeight());
                cpItem.setNetWeight(item.getNetWeight());
                cpItem.setOrderedCartonQuantity(item.getScheduledDeliverableCartonQuantity());
//                cpItem.setOrderedProductQuantity(item.getOrderedProductQuantity());
                cpItem.setTotalVolume(item.getVolume());
                cpItem.setUnit(item.getUnit());
                cpItem.setFunctionDescription(item.getFunctionDescription());
                cpItem.setOrderProductItemId(item.getId());
//                cpItem.setTotalGrossWeight(
//                        (item.getGrossWeight() != null ? item.getGrossWeight() : 0D)
//                                * (item.getOrderedProductQuantity() != null ? item.getOrderedProductQuantity() : 0)
//                );
//                cpItem.setTotalNetWeight(
//                        (item.getNetWeight() != null ? item.getNetWeight() : 0D)
//                                * (item.getOrderedProductQuantity() != null ? item.getOrderedProductQuantity() : 0)
//                );
//                cpItem.setCustomPayment(
//                        (item.getQuotedPrice() != null ? item.getQuotedPrice() : 0D)
//                                * (item.getOrderedProductQuantity() != null ? item.getOrderedProductQuantity() : 0)
//                );
//                cpItem.setFactoryPayment(
//                        (item.getFactoryPrice() != null ? item.getFactoryPrice() : 0D)
//                                * (item.getOrderedProductQuantity() != null ? item.getOrderedProductQuantity() : 0)
//                );
                containerProductItemService.save(cpItem);


                //预装清单产品项
                PreloadedProductItem ppItem = new PreloadedProductItem();
                ppItem.setContainerId(container.getId());
                ppItem.setImageURL(item.getImageURL());
                ppItem.setCompanyProductName(item.getCompanyProductName());
                ppItem.setCompanyProductNo(item.getCompanyProductNo());
                ppItem.setPackageForm(item.getPackageForm());
                ppItem.setCartonSize(item.getCartonSize());
                ppItem.setPackingQuantity(item.getPackingQuantity());
                ppItem.setGrossWeight(item.getGrossWeight());
                ppItem.setNetWeight(item.getNetWeight());
                ppItem.setOrderedCartonQuantity(item.getScheduledDeliverableCartonQuantity());
                ppItem.setTotalVolume(item.getVolume());
                ppItem.setUnit(item.getUnit());
                ppItem.setFunctionDescription(item.getFunctionDescription());
                preloadedProductItemService.save(ppItem);

                Thread.sleep(100);
            }
        }

        modelMap.put("sheetId", sheetOfToday.getId());
        return "redirect:/container/containerSheet";
    }

    @ResponseBody
    @RequestMapping("ajax/container/modify")
    public Object saveContainerInfo(
            @RequestBody Map<String, Object> req
    ) {
        Container container = containerService.getById((String) req.get("containerId"));
        container.setShipmentPort((String) req.get("shipmentPort"));
        container.setDestinationPort((String) req.get("destinationPort"));
        container.setDeliveryDate((String) req.get("deliveryDate"));
        container.setContainerType((String) req.get("containerType"));
        container.setCabinNo((String) req.get("cabinNo"));
        container.setContainerNo((String) req.get("containerNo"));
        container.setSealNo((String) req.get("sealNo"));
        container.setCarrier((String) req.get("carrier"));
        containerService.update(container);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("container/modify")
    public String saveContainerInfo(
            @RequestParam(required = false) String sheetId,
            @RequestParam(required = false) String containerId,
            @RequestParam(required = false) String shipmentPort,
            @RequestParam(required = false) String destinationPort,
            @RequestParam(required = false) String deliveryDate,
            @RequestParam(required = false) String containerType,
            @RequestParam(required = false) String cabinNo,
            @RequestParam(required = false) String containerNo,
            @RequestParam(required = false) String sealNo,
            @RequestParam(required = false) String carrier,
            ModelMap modelMap
    ) {
        Container container = containerService.getById(containerId);
        container.setShipmentPort(shipmentPort);
        container.setDestinationPort(destinationPort);
        container.setDeliveryDate(deliveryDate);
        container.setContainerType(containerType);
        container.setCabinNo(cabinNo);
        container.setContainerNo(containerNo);
        container.setSealNo(sealNo);
        container.setCarrier(carrier);
        containerService.update(container);

        modelMap.put("sheetId", sheetId);
        modelMap.put("containerId", containerId);
        return "redirect:/container/containerSheet";
    }

    @RequestMapping("container/moveItemsToLastContainer")
    public String moveToLastPage(
            @RequestParam String sheetId,
            @RequestParam String containerId,
            @RequestParam List<String> itemIdList,
            ModelMap modelMap
    ) {
//        String sheetId = (String) req.get("sheetId");
//        List<String> itemIdList = (List<String>) req.get("itemIdList");

        Integer lastIndex = containerService.get(
                Projections.max("indexNumber"),
                Integer.class,
                Conditions.newInstance().eq("sheetId", sheetId)
        );

        Container lastContainer = containerService.get(
                Conditions.newInstance().eq("sheetId", sheetId).eq("indexNumber", lastIndex)
        );

        Double currentVolume = 0D;
        if (lastContainer != null) {
            currentVolume = containerProductItemService.get(Projections.sum("totalVolume"), Double.class,
                    Conditions.newInstance().eq("containerId", lastContainer.getId()));
            if (currentVolume == null) {
                currentVolume = 0D;
            }
        }
        for (String itemId : itemIdList) {
            ContainerProductItem item = containerProductItemService.getById(itemId);
            if (currentVolume + item.getTotalVolume() > lastContainer.getContainerVolume()) {
                currentVolume = 0D;
                lastIndex++;
                Container oldLastContainer = lastContainer;
                lastContainer = new Container();
                lastContainer.setSheetId(sheetId);
                lastContainer.setContainerVolume(oldLastContainer.getContainerVolume());
                lastContainer.setIndexNumber(lastIndex);
                containerService.save(lastContainer);
            }

            currentVolume += item.getTotalVolume();
            item.setContainerId(lastContainer.getId());
            containerProductItemService.update(item);
        }

//        Map<String, Object> map = new HashMap<>();
//        map.put("success", true);
//        return map;
        if (StringUtils.isNotBlank(containerId)) {
            modelMap.put("containerId", containerId);
        }
        modelMap.put("sheetId", sheetId);
        return "redirect:/container/containerSheet";
    }

    @ResponseBody
    @RequestMapping("container/getAccumulativeTotal")
    public ContainerAccumulativeTotal getQuotationAccumulativeTotal(
            @RequestBody Map<String, Object> req
    ) {
        String sheetId = (String) req.get("sheetId");

        List<Container> containerList = containerService.getList(
                Conditions.newInstance().eq("sheetId", sheetId)
        );
        List<String> containerIdList = new ArrayList<>();
        for (Container c : containerList) {
            containerIdList.add(c.getId());
        }

        ContainerAccumulativeTotal accumulativeTotal = new ContainerAccumulativeTotal();
        accumulativeTotal.setCartonQuantity(0);
        accumulativeTotal.setProductQuantity(0);
        accumulativeTotal.setVolume(0D);
        accumulativeTotal.setAmount(0D);

        if (!containerIdList.isEmpty()){
            List<ContainerProductItem> itemList = containerProductItemService.getList(
                    Conditions.newInstance().in("containerId", containerIdList)
            );

            for (ContainerProductItem item : itemList) {
                accumulativeTotal.setCartonQuantity(accumulativeTotal.getCartonQuantity() + (item.getOrderedCartonQuantity() != null ? item.getOrderedCartonQuantity() : 0));
                accumulativeTotal.setProductQuantity(accumulativeTotal.getProductQuantity() + (item.getOrderedProductQuantity() != null ? item.getOrderedProductQuantity() : 0));
                accumulativeTotal.setVolume(accumulativeTotal.getVolume() + (item.getTotalVolume() != null ? item.getTotalVolume() : 0D));
                accumulativeTotal.setAmount(accumulativeTotal.getAmount() + (item.getCustomPayment() != null ? item.getCustomPayment() : 0D));
            }
        }
        return accumulativeTotal;
    }

    @RequestMapping("container/preloaded")
    public String preloaded(
            ModelMap modelMap,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        String containerId = userConfigItemService.getValue(user, DEFAULT_CONTAINER);
        if (StringUtils.isNotBlank(containerId)) {
            Container container = containerService.getById(containerId);
            modelMap.put("container", container);

            List<PreloadedProductItem> list = preloadedProductItemService.getList(
                    new Query().setPageSize(Integer.MAX_VALUE)
                    .addOrder(Order.desc("createdTime"))
                    .setConditions(
                            Conditions.newInstance().eq("containerId", containerId)
                    )
            );
            modelMap.put("itemList", list);
        }

        return "container/preloaded";
    }

    @RequestMapping("container/packingList")
    public String packingList(
            @RequestParam String lang,
            ModelMap modelMap,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        String containerId = userConfigItemService.getValue(user, DEFAULT_CONTAINER);
        if (StringUtils.isNotBlank(containerId)) {
            Container container = containerService.getById(containerId);
            modelMap.put("container", container);

            List<PreloadedProductItem> list = preloadedProductItemService.getList(
                    new Query().setPageSize(Integer.MAX_VALUE)
                    .addOrder(Order.desc("createdTime"))
                    .setConditions(
                            Conditions.newInstance().eq("containerId", containerId)
                    )
            );
            modelMap.put("itemList", list);
        }

        return "container/packing-list";
    }

    public static void main(String[] args) {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        System.out.println(c.getTimeInMillis());
//
//        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//        System.out.println(c.getTimeInMillis());


//        System.out.println(c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH));
        for (int i=0; i<10; i++) {
            Calendar c2 = Calendar.getInstance();
            c2.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            System.out.println(c2.getTimeInMillis());

            Calendar c3 = Calendar.getInstance();
            c3.set(2016, 6, 27, 0, 0, 0);
            System.out.println(c3.getTimeInMillis());
        }
    }

}
