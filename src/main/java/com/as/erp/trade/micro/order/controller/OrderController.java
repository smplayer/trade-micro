package com.as.erp.trade.micro.order.controller;

import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.erp.trade.micro.order.entity.FavorCustomer;
import com.as.erp.trade.micro.order.service.FavorCustomerService;
import com.as.erp.trade.micro.order.service.OrderProductItemService;
import com.as.erp.trade.micro.order.service.OrderService;
import com.as.erp.trade.micro.system.service.UserConfigItemService;
import com.as.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 16-4-26.
 */
@Controller
public class OrderController {

    public final static String DEFAULT_FAVOR_CUSTOMER_ID = "default-favor-customer-id";

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductItemService orderProductItemService;
    @Autowired
    private FavorCustomerService favorCustomerService;
    @Autowired
    private UserConfigItemService userConfigItemService;

    @RequestMapping("order")
    public String order(
            @RequestParam(required = false) String favorId,
            @RequestParam(value = "pageIndex", defaultValue = "1") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "orderByFactory", defaultValue = "false") boolean orderByFactory,
            @RequestParam(value = "keywords", required = false) String keywords,
            HttpSession session,
            ModelMap modelMap
    ) {
        User user = (User) session.getAttribute("user");

        String availableFavorId = favorId;
        if (StringUtils.isBlank(availableFavorId)) {
            availableFavorId = userConfigItemService.getValue(user, DEFAULT_FAVOR_CUSTOMER_ID);
            if (availableFavorId == null) {
                FavorCustomer front = favorCustomerService.getFront(user);
                availableFavorId = front.getId();
                userConfigItemService.setValue(user, DEFAULT_FAVOR_CUSTOMER_ID, availableFavorId);
            }
        } else {
            userConfigItemService.setValue(user, DEFAULT_FAVOR_CUSTOMER_ID, availableFavorId);
        }


        if (StringUtils.isNotBlank(availableFavorId)) {
            FavorCustomer favor = favorCustomerService.getById(availableFavorId);
            String customerName = favor.getCustomerName();

            List<com.as.erp.trade.micro.order.entity.Order> orderList = orderService.getList(
                    Conditions.newInstance().eq("customerName", customerName)
            );

            List<String> orderIdList = new ArrayList<>();
            for (com.as.erp.trade.micro.order.entity.Order order : orderList) {
                orderIdList.add(order.getId());
            }

            Query query = new Query()
                    .setPageIndex(pageIndex)
                    .setPageSize(pageSize);

            if (orderByFactory) {
                query.addOrder(Order.asc("factoryId"));
            }

            query.addOrder(Order.desc("createdTime"));

            Conditions conditions = Conditions.newInstance().in("orderId", orderIdList);
            if (StringUtils.isNotBlank(favorId) && StringUtils.isNotBlank(keywords)) {
                conditions.or(
                        Conditions.newInstance()
                                .like("factoryName", "%" + keywords + "%")
                                .like("linkman", "%" + keywords + "%")
                                .like("contactNumber", "%" + keywords + "%")
                                .like("companyProductName", "%" + keywords + "%")
                                .like("companyProductNo", "%" + keywords + "%")
                                .like("packageForm", "%" + keywords + "%")
                                .like("functionDescription", "%" + keywords + "%")
                );
            }
            query.setConditions(conditions);

            PageHandler page = orderProductItemService.getPage(query);

            modelMap.put("page", page);
            modelMap.put("favor", favor);
        }
        return "order/order";
    }

    @ResponseBody
    @RequestMapping("order/favorCustomer/list")
    public Object favorList(
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        List<FavorCustomer> favorList = favorCustomerService.getList(
                new Query().addOrder(Order.asc("createdTime"))
                .setConditions(
                        Conditions.newInstance()
                                .neOrIsNotNull("indexNumber", 0)
//                                .eq("userId", user.getId())
//                                .eq("passwordFlag", user.getRole())
                )
        );

//        Map<String, Object> map = new HashMap<>();
//        map.put("success", true);
//        map.put("favorList", favorList);

        return favorList;
    }

    @RequestMapping(value = "order/favorCustomer/setting",method = RequestMethod.GET)
    public String setFavorCustomer(
            HttpSession session,
            ModelMap modelMap
    ) {

        User user = (User) session.getAttribute("user");
        List<FavorCustomer> sortedList = favorCustomerService.getList(
                new Query().setConditions(
                        Conditions.newInstance()
                        .neOrIsNotNull("indexNumber", 0)
//                        .eq("userId", user.getId())
//                        .eq("passwordFlag", user.getRole())
                ).addOrder(Order.asc("indexNumber"))
        );

        List<FavorCustomer> list = favorCustomerService.getList(
                new Query().setConditions(
                        Conditions.newInstance()
                ).addOrder(Order.asc("createdTime"))
        );

//        modelMap.put("sortedList", sortedList);
        modelMap.put("list", list);

        return "order/favor-customer-setting";
    }

    @RequestMapping(value = "order/favorCustomer/setting", method = RequestMethod.POST)
    public String setFavorCustomer(
            @RequestParam(required = false) Integer indexNumber,
            @RequestParam(required = false) String favorId,
            HttpSession session,
            ModelMap modelMap
    ){
        User user = (User) session.getAttribute("user");
        if (indexNumber != null && StringUtils.isNotBlank(favorId)) {

            FavorCustomer oldItem = favorCustomerService.get(
                    Conditions.newInstance()
                            .eq("indexNumber", indexNumber)
//                            .eq("userId", user.getId())
//                            .eq("passwordFlag", user.getRole())
            );
            if (oldItem != null){
                oldItem.setIndexNumber(null);
                favorCustomerService.update(oldItem);
            }

            FavorCustomer newItem = favorCustomerService.getById(favorId);
            newItem.setIndexNumber(indexNumber);
            favorCustomerService.update(newItem);
        }

        return "order/favor-customer-setting-success";
    }

    @RequestMapping(value = "order/favorCustomer/delete", method = RequestMethod.POST)
    public String deleteFavorCustomer(
            @RequestParam(required = false) String[] favorId,
            @RequestParam(required = false) Integer[] indexNumber,
            HttpSession session,
            ModelMap modelMap
    ){
        User user = (User) session.getAttribute("user");
        String defaultId = userConfigItemService.getValue(user, DEFAULT_FAVOR_CUSTOMER_ID);
        if (favorId != null && favorId.length > 0) {
            List<FavorCustomer> favorCustomerList = favorCustomerService.getList(Conditions.newInstance().in("id", Arrays.asList(favorId)));
            for (FavorCustomer favorCustomer : favorCustomerList) {
                if (favorCustomer.getId().equals(defaultId)) {
                    userConfigItemService.setValue(user, DEFAULT_FAVOR_CUSTOMER_ID, null);
                }
                favorCustomerService.delete(favorCustomer.getId());
            }
        }

        if (indexNumber != null && indexNumber.length > 0) {
            List<FavorCustomer> favorCustomerList = favorCustomerService.getList(
                    Conditions.newInstance()
                            .in("indexNumber", Arrays.asList(indexNumber))
//                            .eq("userId", user.getId())
//                            .eq("passwordFlag", user.getRole())
            );
            for (FavorCustomer favorCustomer : favorCustomerList) {
                if (favorCustomer.getId().equals(defaultId)) {
                    userConfigItemService.setValue(user, DEFAULT_FAVOR_CUSTOMER_ID, null);
                }
                favorCustomer.setIndexNumber(null);
                favorCustomerService.update(favorCustomer);
            }
        }


        return "order/favor-customer-setting-success";
    }

    @RequestMapping("order/addProductByProductNos")
    public String addProductByProductNos(
            @RequestParam String productNoString
    ) {
        String[] productNos = productNoString.trim().split("[,，]");
        return "redirect:/order";
    }

}
