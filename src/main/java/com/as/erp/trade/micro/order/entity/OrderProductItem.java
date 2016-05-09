package com.as.erp.trade.micro.order.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 16-4-25.
 */
@Entity
@Table(name = "as_tb_order_product_item")
public class OrderProductItem extends BaseEntity {
    private String productId;
    private Integer cartonQuantity;
    private Double volume;
    private Double payment;
    private Integer deliveredCartonQuantity;
    private Integer remainingCartonQuantity;
    private Integer scheduledDeliverableCartonQuantity;
    private Double scheduledDeliverableVolume;
    private Double scheduledDeliverablePayment;
    private String remark;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getCartonQuantity() {
        return cartonQuantity;
    }

    public void setCartonQuantity(Integer cartonQuantity) {
        this.cartonQuantity = cartonQuantity;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Integer getDeliveredCartonQuantity() {
        return deliveredCartonQuantity;
    }

    public void setDeliveredCartonQuantity(Integer deliveredCartonQuantity) {
        this.deliveredCartonQuantity = deliveredCartonQuantity;
    }

    public Integer getRemainingCartonQuantity() {
        return remainingCartonQuantity;
    }

    public void setRemainingCartonQuantity(Integer remainingCartonQuantity) {
        this.remainingCartonQuantity = remainingCartonQuantity;
    }

    public Integer getScheduledDeliverableCartonQuantity() {
        return scheduledDeliverableCartonQuantity;
    }

    public void setScheduledDeliverableCartonQuantity(Integer scheduledDeliverableCartonQuantity) {
        this.scheduledDeliverableCartonQuantity = scheduledDeliverableCartonQuantity;
    }

    public Double getScheduledDeliverableVolume() {
        return scheduledDeliverableVolume;
    }

    public void setScheduledDeliverableVolume(Double scheduledDeliverableVolume) {
        this.scheduledDeliverableVolume = scheduledDeliverableVolume;
    }

    public Double getScheduledDeliverablePayment() {
        return scheduledDeliverablePayment;
    }

    public void setScheduledDeliverablePayment(Double scheduledDeliverablePayment) {
        this.scheduledDeliverablePayment = scheduledDeliverablePayment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
