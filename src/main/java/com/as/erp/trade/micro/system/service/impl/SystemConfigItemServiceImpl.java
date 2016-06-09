package com.as.erp.trade.micro.system.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.hibernate.Conditions;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.system.entity.SystemConfigItem;
import com.as.erp.trade.micro.system.dao.SystemConfigItemDao;
import com.as.erp.trade.micro.system.service.SystemConfigItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/5/30.
 */
@Service("systemConfigItemService")
public class SystemConfigItemServiceImpl extends GenericServiceImpl<SystemConfigItem, String> implements SystemConfigItemService {

    @Autowired
    private SystemConfigItemDao systemConfigItemDao;

    @Override
    protected GenericDao<SystemConfigItem, String> getDao() {
        return systemConfigItemDao;
    }

    @Override
    public SystemConfigItem setValue(String key, String value) {
        SystemConfigItem config = get(
                Conditions.newInstance()
                        .eq("key", key)
        );
        if (config == null) {
            config = new SystemConfigItem();
            config.setKey(key);
        }
        config.setValue(value);
        saveOrUpdate(config);
        return config;
    }

    @Override
    public String getValue(String key) {
        SystemConfigItem config = get(
                Conditions.newInstance()
                        .eq("key", key)
        );
        if (config == null) {
            return null;
        } else {
            return config.getValue();
        }
    }
}
