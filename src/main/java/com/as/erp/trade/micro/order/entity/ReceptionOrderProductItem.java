package com.as.erp.trade.micro.order.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 16-4-26.
 */
@Entity
@Table(name = "as_tb_reception_order_product_item")
public class ReceptionOrderProductItem extends BaseEntity {
    private String orderId;
    private String productId;

    private String packageForm;
    private String unit;

    private String name;
    private String imageURL;
    private String factoryProductNo;
    private Double factoryPrice;
    private String cartonSize;
    private String packingQuantity;
    private String grossWeight;
    private String netWeight;
    private Double quotedPrice;
    private String orderedCartonQuantity;
    private String orderedProductQuantity;
    private Double totalVolume;
    private Double amount;
    private String factoryName;
    private String linkman;
    private String contactNumber;
    private String remark;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPackageForm() {
        return packageForm;
    }

    public void setPackageForm(String packageForm) {
        this.packageForm = packageForm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getFactoryProductNo() {
        return factoryProductNo;
    }

    public void setFactoryProductNo(String factoryProductNo) {
        this.factoryProductNo = factoryProductNo;
    }

    public Double getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(Double factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public String getCartonSize() {
        return cartonSize;
    }

    public void setCartonSize(String cartonSize) {
        this.cartonSize = cartonSize;
    }

    public String getPackingQuantity() {
        return packingQuantity;
    }

    public void setPackingQuantity(String packingQuantity) {
        this.packingQuantity = packingQuantity;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public Double getQuotedPrice() {
        return quotedPrice;
    }

    public void setQuotedPrice(Double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public String getOrderedCartonQuantity() {
        return orderedCartonQuantity;
    }

    public void setOrderedCartonQuantity(String orderedCartonQuantity) {
        this.orderedCartonQuantity = orderedCartonQuantity;
    }

    public String getOrderedProductQuantity() {
        return orderedProductQuantity;
    }

    public void setOrderedProductQuantity(String orderedProductQuantity) {
        this.orderedProductQuantity = orderedProductQuantity;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
