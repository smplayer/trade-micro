package com.as.erp.trade.micro.system.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yrx on 2016/5/30.
 */
@Entity
@Table(name = "tb_user_config_item")
public class UserConfigItem extends BaseEntity {
    public final static String USER_FAVOR_MODULE = "user_favor_module";

    private String userId;
    private String passwordFlag;

    private String key;
    private String value;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswordFlag() {
        return passwordFlag;
    }

    public void setPasswordFlag(String passwordFlag) {
        this.passwordFlag = passwordFlag;
    }

    @Column(name = "item_key", nullable = false, unique = false)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "item_value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
