package com.as.erp.trade.micro.factory.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.factory.dao.FactoryDao;
import com.as.erp.trade.micro.factory.entity.Factory;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/4/29.
 */
@Repository("factoryDao")
public class FactoryDaoImpl extends HibernateGenericDaoImpl<Factory, String> implements FactoryDao {
}
