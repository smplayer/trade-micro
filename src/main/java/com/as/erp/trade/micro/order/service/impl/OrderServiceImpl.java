package com.as.erp.trade.micro.order.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.order.dao.OrderDao;
import com.as.erp.trade.micro.order.entity.Order;
import com.as.erp.trade.micro.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/6/24.
 */
@Service("orderService")
public class OrderServiceImpl extends GenericServiceImpl<Order, String> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    protected GenericDao<Order, String> getDao() {
        return orderDao;
    }
}
