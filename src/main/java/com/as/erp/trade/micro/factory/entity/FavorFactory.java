package com.as.erp.trade.micro.factory.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by yrx on 2016/6/3.
 */
@Entity
public class FavorFactory extends BaseEntity {

    private String userId;
    private String factoryId;
    private String factoryName;
    private Integer indexNumber;

    private String passwordFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getPasswordFlag() {
        return passwordFlag;
    }

    public void setPasswordFlag(String passwordFlag) {
        this.passwordFlag = passwordFlag;
    }
}
