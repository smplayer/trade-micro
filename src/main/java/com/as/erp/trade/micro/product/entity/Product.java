package com.as.erp.trade.micro.product.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 16-4-25.
 */
@Entity
@Table(name = "as_tb_product")
public class Product extends BaseEntity {

    public final static String PRODUCT_STATUS_COMPLETE = "complete";
    public final static String PRODUCT_STATUS_IMCOMPLETE = "incomplete";

    private String name;
    private String imageURL;
    private String factoryProductName;
    private String companyProductName;
    private String factoryProductNo;
    private String companyProductNo;
    private Double factoryPrice;
    private Long lastFactoryQuotedTime;
    private String cartonSize;
    private Integer packingQuantity;
    private Double grossWeight;
    private Double netWeight;
    private String unit;
    private String remark;

    //引用工厂信息
    private String factoryId;
    private String factoryName;
    private String linkman;
    private String factoryContactNumber;

    //以下信息来源可能改为引用
    private String packageForm;
    private String functionDescription;
    private String category;
    private String subCategory;

    private Date addedDate;
    private String productStatus;

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

    public String getFactoryProductName() {
        return factoryProductName;
    }

    public void setFactoryProductName(String factoryProductName) {
        this.factoryProductName = factoryProductName;
    }

    public String getCompanyProductName() {
        return companyProductName;
    }

    public void setCompanyProductName(String companyProductName) {
        this.companyProductName = companyProductName;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getFactoryContactNumber() {
        return factoryContactNumber;
    }

    public void setFactoryContactNumber(String factoryContactNumber) {
        this.factoryContactNumber = factoryContactNumber;
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
