package com.as.erp.trade.micro.container.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 16-4-26.
 */
@Entity
@Table(name = "as_tb_container_product_item")
public class ContainerProductItem extends BaseEntity {
    private String orderProductItemId;
    private String cartonQuantity;
    private String totalProductQuantity;
    private Double totalVolume;
    private Double totalGrossWeight;
    private Double totalNetWeight;
    private Double factoryPayment;
    private Double customPayment;
    private String remark;

    public String getOrderProductItemId() {
        return orderProductItemId;
    }

    public void setOrderProductItemId(String orderProductItemId) {
        this.orderProductItemId = orderProductItemId;
    }

    public String getCartonQuantity() {
        return cartonQuantity;
    }

    public void setCartonQuantity(String cartonQuantity) {
        this.cartonQuantity = cartonQuantity;
    }

    public String getTotalProductQuantity() {
        return totalProductQuantity;
    }

    public void setTotalProductQuantity(String totalProductQuantity) {
        this.totalProductQuantity = totalProductQuantity;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getTotalGrossWeight() {
        return totalGrossWeight;
    }

    public void setTotalGrossWeight(Double totalGrossWeight) {
        this.totalGrossWeight = totalGrossWeight;
    }

    public Double getTotalNetWeight() {
        return totalNetWeight;
    }

    public void setTotalNetWeight(Double totalNetWeight) {
        this.totalNetWeight = totalNetWeight;
    }

    public Double getFactoryPayment() {
        return factoryPayment;
    }

    public void setFactoryPayment(Double factoryPayment) {
        this.factoryPayment = factoryPayment;
    }

    public Double getCustomPayment() {
        return customPayment;
    }

    public void setCustomPayment(Double customPayment) {
        this.customPayment = customPayment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
