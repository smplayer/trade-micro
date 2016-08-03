package com.as.erp.trade.micro.container.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.container.dao.ContainerSheetDao;
import com.as.erp.trade.micro.container.entity.ContainerSheet;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/7/26.
 */
@Repository("containerSheetDao")
public class ContainerSheetDaoImpl extends HibernateGenericDaoImpl<ContainerSheet, String> implements ContainerSheetDao {
}
