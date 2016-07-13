package com.as.erp.trade.micro.order.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.hibernate.Conditions;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.order.dao.FavorCustomerDao;
import com.as.erp.trade.micro.order.entity.FavorCustomer;
import com.as.erp.trade.micro.order.service.FavorCustomerService;
import com.as.user.entity.User;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/6/27.
 */
@Service
public class FavorCustomerServiceImpl extends GenericServiceImpl<FavorCustomer, String> implements FavorCustomerService {

    @Autowired
    private FavorCustomerDao favorCustomerDao;

    @Override
    protected GenericDao<FavorCustomer, String> getDao() {
        return favorCustomerDao;
    }


    @Override
    public FavorCustomer getFront(User user) {
        Integer minIndexNumber = get(Projections.min("indexNumber"), Integer.class);
        FavorCustomer favorCustomer = get(
                Conditions.newInstance()
//                        .eq("userId", user.getId())
//                        .eq("passwordFlag", user.getRole())
                        .eq("indexNumber", minIndexNumber));
        return favorCustomer;
    }
}
