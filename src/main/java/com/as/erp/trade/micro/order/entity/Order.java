package com.as.erp.trade.micro.order.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 16-4-25.
 */
@Entity
@Table(name = "as_tb_order")
public class Order extends BaseEntity {
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
