package com.as.erp.trade.micro.system.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yrx on 2016/5/30.
 */
@Entity
@Table(name = "tb_system_config_item")
public class SystemConfigItem extends BaseEntity {
    public final static String PRODUCT_N0_PREFIX = "product_no_prefix";
    public final static String PRODUCT_NO_BEGIN = "product_no_begin";
//    public final static String START_QUOTATION_SERIAL_NUMBER = "start_quotation_serial_number";


    private String key;
    private String value;

    @Column(name = "item_key", nullable = false, unique = true)
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
