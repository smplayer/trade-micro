package com.as.erp.trade.micro.container.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.container.dao.ContainerProductItemDao;
import com.as.erp.trade.micro.container.entity.ContainerProductItem;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/7/24.
 */
@Repository("containerProductItemDao")
public class ContainerProductItemDaoImpl extends HibernateGenericDaoImpl<ContainerProductItem, String> implements ContainerProductItemDao {
}
