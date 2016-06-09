package com.as.erp.trade.micro.system.service;

import com.as.common.service.GenericService;
import com.as.erp.trade.micro.system.entity.UserConfigItem;
import com.as.user.entity.User;

/**
 * Created by yrx on 2016/6/1.
 */
public interface UserConfigItemService extends GenericService<UserConfigItem, String> {
    UserConfigItem setValue(User user, String key, String value);
    String getValue(User user, String key);
}
