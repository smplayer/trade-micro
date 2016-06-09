package com.as.erp.trade.micro.system.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.system.entity.SystemConfigItem;
import com.as.erp.trade.micro.system.dao.SystemConfigItemDao;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/5/30.
 */
@Repository("systemConfigItemDao")
public class SystemConfigItemDaoImpl extends HibernateGenericDaoImpl<SystemConfigItem, String> implements SystemConfigItemDao {
}
