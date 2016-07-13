package com.as.erp.trade.micro.factory.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.factory.FactoryDeleteEvent;
import com.as.erp.trade.micro.factory.FactoryNameModifiedEvent;
import com.as.erp.trade.micro.factory.dao.FactoryDao;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by yrx on 2016/4/29.
 */
@Service("factoryService")
public class FactoryServiceImpl extends GenericServiceImpl<Factory, String> implements FactoryService {

    @Autowired
    private FactoryDao factoryDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected GenericDao<Factory, String> getDao() {
        return factoryDao;
    }

    @Override
    public void save(Factory entity) {
        entity.setAddedDate(new Date());
        super.save(entity);
    }

    @Override
    public void delete(String id) {
        super.delete(id);
        applicationContext.publishEvent(new FactoryDeleteEvent(id));
    }
}
