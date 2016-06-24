package com.as.erp.trade.micro.order.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.order.dao.OrderProductItemDao;
import com.as.erp.trade.micro.order.entity.OrderProductItem;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/6/24.
 */
@Repository("orderProductItemDao")
public class OrderProductItemDaoImpl extends HibernateGenericDaoImpl<OrderProductItem, String> implements OrderProductItemDao{
}
