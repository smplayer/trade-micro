package com.as.erp.trade.micro.container.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yrx on 2016/7/31.
 */
@Entity
@Table(name = "as_tb_preloaded")
public class PreloadedProductItem extends BaseEntity {

    private String containerId;
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

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
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
        return quotedPrice;
    }

    public void setQuotedPrice(Double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public Double getFactoryPrice() {
        return factoryPrice;
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
        return packingQuantity;
    }

    public void setPackingQuantity(Integer packingQuantity) {
        this.packingQuantity = packingQuantity;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Integer getOrderedCartonQuantity() {
        return orderedCartonQuantity;
    }

    public void setOrderedCartonQuantity(Integer orderedCartonQuantity) {
        this.orderedCartonQuantity = orderedCartonQuantity;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }
}
