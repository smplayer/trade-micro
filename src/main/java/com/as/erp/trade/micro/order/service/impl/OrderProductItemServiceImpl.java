package com.as.erp.trade.micro.order.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.PageHandler;
import com.as.common.query.hibernate.Conditions;
import com.as.common.query.hibernate.Query;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.order.dao.OrderProductItemDao;
import com.as.erp.trade.micro.order.entity.FavorCustomer;
import com.as.erp.trade.micro.order.entity.OrderProductItem;
import com.as.erp.trade.micro.order.service.FavorCustomerService;
import com.as.erp.trade.micro.order.service.OrderProductItemService;
import com.as.erp.trade.micro.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yrx on 2016/6/24.
 */
@Service("orderProductItemService")
public class OrderProductItemServiceImpl extends GenericServiceImpl<OrderProductItem, String> implements OrderProductItemService {

    @Autowired
    private OrderProductItemDao orderProductItemDao;

    @Autowired
    private FavorCustomerService favorCustomerService;

    @Autowired
    private OrderService orderService;

    @Override
    protected GenericDao<OrderProductItem, String> getDao() {
        return orderProductItemDao;
    }

    @Override
    public List<OrderProductItem> getListByFavor(String favorId) {
        FavorCustomer favor = favorCustomerService.getById(favorId);
        if (favor != null) {
            String customerName = favor.getCustomerName();

            List<com.as.erp.trade.micro.order.entity.Order> orderList = orderService.getList(
                    Conditions.newInstance().eq("customerName", customerName)
            );

            List<String> orderIdList = new ArrayList<>();
            for (com.as.erp.trade.micro.order.entity.Order order : orderList) {
                orderIdList.add(order.getId());
            }

            Query query = new Query()
                    .setPageSize(Integer.MAX_VALUE)
                    .addOrder(Order.desc("createdTime"));

            Conditions conditions = Conditions.newInstance().in("orderId", orderIdList);
            query.setConditions(conditions);

            return getList(query);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<OrderProductItem> getScheduledListByFavor(String favorId) {
        FavorCustomer favor = favorCustomerService.getById(favorId);
        if (favor != null) {
            String customerName = favor.getCustomerName();

            List<com.as.erp.trade.micro.order.entity.Order> orderList = orderService.getList(
                    Conditions.newInstance().eq("customerName", customerName)
            );

            List<String> orderIdList = new ArrayList<>();
            for (com.as.erp.trade.micro.order.entity.Order order : orderList) {
                orderIdList.add(order.getId());
            }

            Query query = new Query()
                    .setPageSize(Integer.MAX_VALUE)
                    .addOrder(Order.desc("createdTime"));

            Conditions conditions = Conditions.newInstance()
                    .in("orderId", orderIdList);
            // TO-DO 增加装运计划条件限制
            query.setConditions(conditions);

            return getList(query);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public OrderProductItem modifyProp(String id, String propName, Object propValue) {

        OrderProductItem item = null;
        if ("cartonSize".equals(propName)) {
            item = _modifyProp(id, propName, propValue);
            reCalVolume(item);
        } else if ("packingQuantity".equals(propName)) {
            item = _modifyProp(id, propName, Integer.valueOf(propValue.toString()));
            reCalVolume(item);
            reCalPayment(item);
        } else if ("grossWeight".equals(propName)) {
            item = _modifyProp(id, propName, Double.valueOf(propValue.toString()));
        } else if ("netWeight".equals(propName)) {
            item = _modifyProp(id, propName, Double.valueOf(propValue.toString()));
        } else if ("orderedCartonQuantity".equals(propName)) {
            item = _modifyProp(id, propName, Integer.valueOf(propValue.toString()));
            reCalVolume(item);
            reCalPayment(item);
            reCalRemainingCartonQuantity(item);
        } else if ("scheduledDeliverableCartonQuantity".equals(propName)) {
            item = _modifyProp(id, propName, Integer.valueOf(propValue != null && StringUtils.isNoneBlank(propValue.toString()) ? propValue.toString() : "0"));
//            if (item.getScheduledDeliverableCartonQuantity() > item.getRemainingCartonQuantity()) {
//                item.setScheduledDeliverableCartonQuantity(0);
//            }
            reCalScheduledDeliverableVolume(item);
            reCalScheduledDeliverablePayment(item);
        } else {
            item = _modifyProp(id, propName, propValue);
        }

        update(item);
        return item;
    }

    private OrderProductItem _modifyProp(String id, String propName, Object propValue) {
        OrderProductItem item = getById(id);
        try {
            Field field;
            field = item.getClass().getDeclaredField(propName);
            field.setAccessible(true);
            field.set(item, propValue);
//            update(item);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return item;
    }

    private void reCalVolume(OrderProductItem item) {
        boolean flag = true;

        if (item.getCartonSize() != null) {
            Pattern p = Pattern.compile("^[1-9]+[0-9]*[Xx\\*][1-9]+[0-9]*[Xx\\*][1-9]+[0-9]*$");
            Matcher m = p.matcher(item.getCartonSize());
            if (m.matches()) {
                String cartonSize = item.getCartonSize();
                String[] sizeElems = cartonSize.split("[\\*xX]");
                Double volume = Double.valueOf(sizeElems[0]) * Double.valueOf(sizeElems[1]) * Double.valueOf(sizeElems[2]) / (100 * 100 * 100);
                item.setVolume(volume * item.getOrderedCartonQuantity());
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        if (!flag) {
            item.setVolume(0D);
        }
    }

    private void reCalPayment(OrderProductItem item) {
        if (item.getFactoryPrice() != null && item.getOrderedCartonQuantity() != null && item.getPackingQuantity() != null)
            item.setPayment(item.getFactoryPrice() * item.getOrderedCartonQuantity() * item.getPackingQuantity());
    }

    private void reCalRemainingCartonQuantity(OrderProductItem item) {
        Integer remain = 0;
        if (item.getOrderedCartonQuantity() != null) {
            if (item.getDeliveredCartonQuantity() != null) {
                remain = item.getOrderedCartonQuantity() - item.getDeliveredCartonQuantity();
            } else {
                remain = item.getOrderedCartonQuantity();
            }
        }
        item.setRemainingCartonQuantity(remain);
    }

    private void reCalScheduledDeliverableVolume(OrderProductItem item) {
        boolean flag = true;

        if (item.getCartonSize() != null) {
            Pattern p = Pattern.compile("^[1-9]+[0-9]*[Xx\\*][1-9]+[0-9]*[Xx\\*][1-9]+[0-9]*$");
            Matcher m = p.matcher(item.getCartonSize());
            if (m.matches()) {
                String cartonSize = item.getCartonSize();
                String[] sizeElems = cartonSize.split("[\\*xX]");
                Double volume = Double.valueOf(sizeElems[0]) * Double.valueOf(sizeElems[1]) * Double.valueOf(sizeElems[2]) / (100 * 100 * 100);
                item.setScheduledDeliverableVolume(volume * (item.getScheduledDeliverableCartonQuantity() != null ? item.getScheduledDeliverableCartonQuantity() : 0));
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        if (!flag) {
            item.setVolume(0D);
        }
    }

    private void reCalScheduledDeliverablePayment(OrderProductItem item) {
        if (item.getScheduledDeliverableCartonQuantity() != null && item.getScheduledDeliverableCartonQuantity() != null && item.getPackingQuantity() != null)
            item.setScheduledDeliverablePayment(item.getFactoryPrice() * item.getScheduledDeliverableCartonQuantity() * item.getPackingQuantity());
    }


    @Override
    public Map<String, Object> getAccumulativeTotal(String favorId, Long pageIndex, Integer pageSize) {

        FavorCustomer favorCustomer = favorCustomerService.getById(favorId);

        List<com.as.erp.trade.micro.order.entity.Order> orderList = orderService.getList(
                new Query()
                        .setPageSize(Integer.MAX_VALUE)
                        .setConditions(
                                Conditions.newInstance()
                                        .eq("customerName", favorCustomer.getCustomerName())
                        )
        );

        List<String> orderIdList = new ArrayList<>();
        for (com.as.erp.trade.micro.order.entity.Order order : orderList) {
            orderIdList.add(order.getId());
        }

        List<OrderProductItem> itemList = getList(
                new Query().setDataIndex((pageIndex - 1) * pageSize)
                        .setPageSize(Integer.MAX_VALUE)
                        .setConditions(
                                Conditions.newInstance()
                                        .in("orderId", orderIdList)
                        )
                        .addOrder(Order.desc("createdTime"))
        );


        Map<String, Object> map = new HashMap<>();
        map.put("cartonQuantity", 0);
        map.put("volume", 0D);
        map.put("payment", 0D);
        map.put("deliveredCartonQuantity", 0);
        map.put("remainingCartonQuantity", 0);
        map.put("scheduledDeliverableCartonQuantity", 0);
        map.put("scheduledDeliverableVolume", 0D);
        map.put("scheduledDeliverablePayment", 0D);

        for (OrderProductItem item : itemList) {
            map.put("cartonQuantity", (Integer) map.get("cartonQuantity") + (item.getOrderedCartonQuantity() != null ? item.getOrderedCartonQuantity() : 0));
            map.put("volume", (Double) map.get("volume") + (item.getVolume() != null ? item.getVolume() : 0D));
            map.put("payment", (Double) map.get("payment") + (item.getPayment() != null ? item.getPayment() : 0D));
            map.put("deliveredCartonQuantity", (Integer) map.get("deliveredCartonQuantity") + (item.getDeliveredCartonQuantity() != null ? item.getDeliveredCartonQuantity() : 0));
            map.put("remainingCartonQuantity", (Integer) map.get("remainingCartonQuantity") + (item.getRemainingCartonQuantity() != null ? item.getRemainingCartonQuantity() : 0));
            map.put("scheduledDeliverableCartonQuantity", (Integer) map.get("scheduledDeliverableCartonQuantity") + (item.getScheduledDeliverableCartonQuantity() != null ? item.getScheduledDeliverableCartonQuantity() : 0));
            map.put("scheduledDeliverableVolume", (Double) map.get("scheduledDeliverableVolume") + (item.getScheduledDeliverableVolume() != null ? item.getScheduledDeliverableVolume() : 0D));
            map.put("scheduledDeliverablePayment", (Double) map.get("scheduledDeliverablePayment") + (item.getScheduledDeliverablePayment() != null ? item.getScheduledDeliverablePayment() : 0D));
        }
        return map;
    }

}
