package com.as.erp.trade.micro.system.dao.impl;

import com.as.common.dao.impl.HibernateGenericDaoImpl;
import com.as.erp.trade.micro.system.dao.UserConfigItemDao;
import com.as.erp.trade.micro.system.entity.UserConfigItem;
import org.springframework.stereotype.Repository;

/**
 * Created by yrx on 2016/6/1.
 */
@Repository("userConfigItemDao")
public class UserConfigItemDaoImpl extends HibernateGenericDaoImpl<UserConfigItem, String> implements UserConfigItemDao{
}
