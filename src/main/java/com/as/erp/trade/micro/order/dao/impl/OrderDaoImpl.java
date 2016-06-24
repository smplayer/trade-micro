package com.as.erp.trade.micro.order.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.order.dao.OrderDao;
import com.as.erp.trade.micro.order.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/6/24.
 */
@Repository("orderDao")
public class OrderDaoImpl extends HibernateGenericDaoImpl<Order, String> implements OrderDao {
}
