package com.as.erp.trade.micro.system;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by yrx on 2016/5/19.
 */
@Entity
public class Config extends BaseEntity {

    private String startProductNumber;
    private String startQuotationSerialNumber;

    public String getStartProductNumber() {
        return startProductNumber;
    }

    public void setStartProductNumber(String startProductNumber) {
        this.startProductNumber = startProductNumber;
    }

    public String getStartQuotationSerialNumber() {
        return startQuotationSerialNumber;
    }

    public void setStartQuotationSerialNumber(String startQuotationSerialNumber) {
        this.startQuotationSerialNumber = startQuotationSerialNumber;
    }
}
