package com.as.erp.trade.micro.quotation.vo;

/**
 * Created by yrx on 2016/5/22.
 */
public class ArchiveTotalValues {
    private Double containerQuantity;
    private Integer cartonQuantity;
    private Integer productQuantity;
    private Double volume;
    private Double amount;

    public Double getContainerQuantity() {
        return containerQuantity;
    }

    public void setContainerQuantity(Double containerQuantity) {
        this.containerQuantity = containerQuantity;
    }

    public Integer getCartonQuantity() {
        return cartonQuantity;
    }

    public void setCartonQuantity(Integer cartonQuantity) {
        this.cartonQuantity = cartonQuantity;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
