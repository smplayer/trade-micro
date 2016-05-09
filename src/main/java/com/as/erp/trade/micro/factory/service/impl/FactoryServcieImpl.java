package com.as.erp.trade.micro.factory.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.factory.dao.FactoryDao;
import com.as.erp.trade.micro.factory.entity.Factory;
import com.as.erp.trade.micro.factory.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/4/29.
 */
@Service("factoryService")
public class FactoryServcieImpl extends GenericServiceImpl<Factory, String> implements FactoryService {

    @Autowired
    private FactoryDao factoryDao;

    @Override
    protected GenericDao<Factory, String> getDao() {
        return factoryDao;
    }
}
