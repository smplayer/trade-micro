package com.as.erp.trade.micro.quotation.entity;

/**
 * Created by yrx on 2016/5/4.
 */

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_quotation_module_config")
public class QuotationModuleConfig extends BaseEntity {

    private String operatingQuotationId;

    public String getOperatingQuotationId() {
        return operatingQuotationId;
    }

    public void setOperatingQuotationId(String operatingQuotationId) {
        this.operatingQuotationId = operatingQuotationId;
    }
}
