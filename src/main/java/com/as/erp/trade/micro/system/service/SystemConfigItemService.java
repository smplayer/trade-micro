package com.as.erp.trade.micro.system.service;

import com.as.common.service.GenericService;
import com.as.erp.trade.micro.system.entity.SystemConfigItem;

/**
 * Created by yrx on 2016/5/30.
 */
public interface SystemConfigItemService extends GenericService<SystemConfigItem, String> {
    SystemConfigItem setValue(String key, String value);
    String getValue(String key);
}
