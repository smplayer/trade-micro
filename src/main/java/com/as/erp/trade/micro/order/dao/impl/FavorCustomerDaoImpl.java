package com.as.erp.trade.micro.order.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.order.dao.FavorCustomerDao;
import com.as.erp.trade.micro.order.entity.FavorCustomer;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/6/27.
 */
@Repository
public class FavorCustomerDaoImpl extends HibernateGenericDaoImpl<FavorCustomer, String> implements FavorCustomerDao {
}
