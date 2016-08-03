package com.as.erp.trade.micro.container.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.container.dao.ContainerDao;
import com.as.erp.trade.micro.container.entity.Container;
import com.as.erp.trade.micro.container.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/7/21.
 */
@Service("containerService")
public class ContainerServiceImpl extends GenericServiceImpl<Container, String> implements ContainerService {
    @Autowired
    private ContainerDao containerDao;

    @Override
    protected GenericDao<Container, String> getDao() {
        return containerDao;
    }
}
