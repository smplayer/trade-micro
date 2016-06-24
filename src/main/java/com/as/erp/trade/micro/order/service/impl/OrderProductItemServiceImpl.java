package com.as.erp.trade.micro.order.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.order.dao.OrderProductItemDao;
import com.as.erp.trade.micro.order.entity.OrderProductItem;
import com.as.erp.trade.micro.order.service.OrderProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/6/24.
 */
@Service("orderProductItemService")
public class OrderProductItemServiceImpl extends GenericServiceImpl<OrderProductItem, String> implements OrderProductItemService {

    @Autowired
    private OrderProductItemDao orderProductItemDao;

    @Override
    protected GenericDao<OrderProductItem, String> getDao() {
        return orderProductItemDao;
    }
}
