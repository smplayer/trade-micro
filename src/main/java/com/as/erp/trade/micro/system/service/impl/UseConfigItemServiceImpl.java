package com.as.erp.trade.micro.system.service.impl;

import com.as.common.dao.GenericDao;
import com.as.common.query.hibernate.Conditions;
import com.as.common.service.impl.GenericServiceImpl;
import com.as.erp.trade.micro.system.dao.UserConfigItemDao;
import com.as.erp.trade.micro.system.entity.UserConfigItem;
import com.as.erp.trade.micro.system.service.UserConfigItemService;
import com.as.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yrx on 2016/6/1.
 */
@Service("userConfigItemService")
public class UseConfigItemServiceImpl extends GenericServiceImpl<UserConfigItem, String> implements UserConfigItemService {

    @Autowired
    private UserConfigItemDao userConfigItemDao;

    @Override
    protected GenericDao<UserConfigItem, String> getDao() {
        return userConfigItemDao;
    }

    @Override
    public UserConfigItem setValue(User user, String key, String value) {
        UserConfigItem config = get(
                Conditions.newInstance()
                        .eq("userId", user.getId())
                        .eq("passwordFlag", user.getRole())
                        .eq("key", key)
        );
        if (config == null) {
            config = new UserConfigItem();
            config.setUserId(user.getId());
            config.setPasswordFlag(user.getRole());
            config.setKey(key);
        }
        config.setValue(value);
        saveOrUpdate(config);
        return config;
    }

    @Override
    public String getValue(User user, String key) {
        UserConfigItem config = get(
                Conditions.newInstance()
                        .eq("userId", user.getId())
                        .eq("passwordFlag", user.getRole())
                        .eq("key", key)
        );
        if (config != null) {
            return config.getValue();
        } else {
            return null;
        }
    }
}
