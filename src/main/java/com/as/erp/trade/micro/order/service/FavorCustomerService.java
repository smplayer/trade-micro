package com.as.erp.trade.micro.order.service;

import com.as.common.service.GenericService;
import com.as.erp.trade.micro.order.entity.FavorCustomer;
import com.as.user.entity.User;

/**
 * Created by yrx on 2016/6/27.
 */
public interface FavorCustomerService extends GenericService<FavorCustomer, String> {
    FavorCustomer getFront(User user);
}
