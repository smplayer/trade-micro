package com.as.erp.trade.micro.quotation.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yrx on 2016/5/27.
 */
@Entity
@Table(name = "as_tb_favor_quotation_item")
public class FavorQuotationItem extends BaseEntity {
    private String userId;
    private String quotationId;
    private String customerName;
    private Integer indexNumber;

    private String passwordFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPasswordFlag() {
        return passwordFlag;
    }

    public void setPasswordFlag(String passwordFlag) {
        this.passwordFlag = passwordFlag;
    }
}
