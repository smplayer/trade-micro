package com.as.erp.trade.micro.container.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by Administrator on 16-4-26.
 */
@Entity
@Table(name = "as_tb_container_product_item")
public class ContainerProductItem extends BaseEntity {
    private String containerId;
    private String orderProductItemId;
    private String imageURL;
    private String companyProductName;
    private String companyProductNo;
    private String packageForm;
    private String unit;
    private Double quotedPrice;
    private Double factoryPrice;
    private String functionDescription;
    private String cartonSize;
    private Integer packingQuantity;
    private Double grossWeight;
    private Double netWeight;
    private Integer orderedCartonQuantity;
//    private Integer orderedProductQuantity;
    private Double totalVolume;

//    private Double totalGrossWeight;
//    private Double totalNetWeight;
//    private Double factoryPayment;
//    private Double customPayment;
    private String remark;

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getOrderProductItemId() {
        return orderProductItemId;
    }

    public void setOrderProductItemId(String orderProductItemId) {
        this.orderProductItemId = orderProductItemId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCompanyProductName() {
        return companyProductName;
    }

    public void setCompanyProductName(String companyProductName) {
        this.companyProductName = companyProductName;
    }

    public String getCompanyProductNo() {
        return companyProductNo;
    }

    public void setCompanyProductNo(String companyProductNo) {
        this.companyProductNo = companyProductNo;
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

    public Double getQuotedPrice() {
        return quotedPrice != null ? quotedPrice : 0D;
    }

    public void setQuotedPrice(Double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public Double getFactoryPrice() {
        return factoryPrice != null ? factoryPrice : 0D;
    }

    public void setFactoryPrice(Double factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public String getCartonSize() {
        return cartonSize;
    }

    public void setCartonSize(String cartonSize) {
        this.cartonSize = cartonSize;
    }

    public Integer getPackingQuantity() {
        return packingQuantity != null ? packingQuantity : 0;
    }

    public void setPackingQuantity(Integer packingQuantity) {
        this.packingQuantity = packingQuantity;
    }

    public Double getGrossWeight() {
        return grossWeight != null ? grossWeight : 0;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getNetWeight() {
        return netWeight != null ? netWeight : 0;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Integer getOrderedCartonQuantity() {
        return orderedCartonQuantity != null ? orderedCartonQuantity : 0;
    }

    public void setOrderedCartonQuantity(Integer orderedCartonQuantity) {
        this.orderedCartonQuantity = orderedCartonQuantity;
    }

    @Transient
    public Integer getOrderedProductQuantity() {
        return getOrderedCartonQuantity() * getPackingQuantity();
    }

    public Double getTotalVolume() {
        return totalVolume != null ? totalVolume : 0;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    @Transient
    public Double getTotalGrossWeight() {
        return getOrderedCartonQuantity() * getGrossWeight();
    }

//    public void setTotalGrossWeight(Double totalGrossWeight) {
//        this.totalGrossWeight = totalGrossWeight;
//    }

    @Transient
    public Double getTotalNetWeight() {
        return getOrderedCartonQuantity() * getNetWeight();
    }

//    public void setTotalNetWeight(Double totalNetWeight) {
//        this.totalNetWeight = totalNetWeight;
//    }

    @Transient
    public Double getFactoryPayment() {
//        return factoryPayment != null ? factoryPayment : 0;
        return getOrderedProductQuantity() * getFactoryPrice();
    }

//    public void setFactoryPayment(Double factoryPayment) {
//        this.factoryPayment = factoryPayment;
//    }

    @Transient
    public Double getCustomPayment() {
        return getOrderedProductQuantity() * getQuotedPrice();
    }

//    public void setCustomPayment(Double customPayment) {
//        this.customPayment = customPayment;
//    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
