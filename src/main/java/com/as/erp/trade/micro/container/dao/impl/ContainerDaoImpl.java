package com.as.erp.trade.micro.container.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.container.dao.ContainerDao;
import com.as.erp.trade.micro.container.entity.Container;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/7/21.
 */
@Repository("containerDao")
public class ContainerDaoImpl extends HibernateGenericDaoImpl<Container, String> implements ContainerDao {
}
