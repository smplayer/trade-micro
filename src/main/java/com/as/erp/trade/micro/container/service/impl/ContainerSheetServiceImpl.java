package com.as.erp.trade.micro.container.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.container.dao.ContainerSheetDao;
import com.as.erp.trade.micro.container.entity.ContainerSheet;
import com.as.erp.trade.micro.container.service.ContainerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/7/26.
 */
@Service("containerSheetService")
public class ContainerSheetServiceImpl extends GenericServiceImpl<ContainerSheet, String> implements ContainerSheetService {

    @Autowired
    private ContainerSheetDao containerSheetDao;

    @Override
    protected GenericDao<ContainerSheet, String> getDao() {
        return containerSheetDao;
    }
}
