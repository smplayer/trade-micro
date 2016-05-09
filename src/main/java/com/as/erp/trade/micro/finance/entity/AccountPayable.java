package com.as.erp.trade.micro.finance.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 16-4-26.
 */
@Entity
@Table(name = "as_tb_account_payable")
public class AccountPayable extends BaseEntity {
    private String orderId;
    private String containerId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
}
