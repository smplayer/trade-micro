package com.as.erp.trade.micro.product.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 16-4-25.
 */
@Entity
@Table(name = "as_tb_product_details")
public class ProductDetails extends BaseEntity {
    private String productId;
    private String name;
    private String imageURL;
    private String factoryProductNo;
    private String companyProductNo;
    private Double factoryPrice;
    private Long lastFactoryQuotedTime;
    private String cartonSize;
    private Integer packingQuantity;
    private Double grossWeight;
    private Double netWeight;
    private String remark;

    //引用工厂信息
    private String factoryId;

    //以下信息来源可能改为引用
    private String packageForm;
    private String functionDescription;
    private String category;
    private String subCategory;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getCompanyProductNo() {
        return companyProductNo;
    }

    public void setCompanyProductNo(String companyProductNo) {
        this.companyProductNo = companyProductNo;
    }

    public Double getFactoryPrice() {
        return factoryPrice;
    }

    public void setFactoryPrice(Double factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public Long getLastFactoryQuotedTime() {
        return lastFactoryQuotedTime;
    }

    public void setLastFactoryQuotedTime(Long lastFactoryQuotedTime) {
        this.lastFactoryQuotedTime = lastFactoryQuotedTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getPackageForm() {
        return packageForm;
    }

    public void setPackageForm(String packageForm) {
        this.packageForm = packageForm;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}
