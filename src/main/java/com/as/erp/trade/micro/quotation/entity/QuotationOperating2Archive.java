package com.as.erp.trade.micro.quotation.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yrx on 2016/5/18.
 */
@Entity
@Table(name = "as_tb_quotation_operating2archive")
public class QuotationOperating2Archive extends BaseEntity {

    private String operatingId;
    private String archiveId;

    public String getOperatingId() {
        return operatingId;
    }

    public void setOperatingId(String operatingId) {
        this.operatingId = operatingId;
    }

    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }
}
