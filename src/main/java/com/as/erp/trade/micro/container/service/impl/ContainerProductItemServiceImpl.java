package com.as.erp.trade.micro.container.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.container.dao.ContainerProductItemDao;
import com.as.erp.trade.micro.container.entity.ContainerProductItem;
import com.as.erp.trade.micro.container.service.ContainerProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/7/24.
 */
@Service("containerProductItemService")
public class ContainerProductItemServiceImpl extends GenericServiceImpl<ContainerProductItem, String> implements ContainerProductItemService {

    @Autowired
    private ContainerProductItemDao containerProductItemDao;

    @Override
    protected GenericDao<ContainerProductItem, String> getDao() {
        return containerProductItemDao;
    }
}
