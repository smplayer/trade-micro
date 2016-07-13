package com.as.erp.trade.micro.quotation.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by yrx on 2016/5/12.
 */
@Entity
@Table(name = "as_tb_quotation_product_item_draft")
public class QuotationProductItemDraft extends BaseEntity {

    private String quotationId;

    private String productId;
    private String imageURL;
    private String factoryProductName;
    private String factoryProductNo;
    private String companyProductName;
    private String companyProductNo;
    private String functionDescription;
    private String packageForm;
    private String unit;
    private Double factoryPrice;
    private String cartonSize;
    private Double cartonVolume;
    private Integer packingQuantity;
    private Double grossWeight;
    private Double netWeight;
    private Integer orderedCartonQuantity;
    private Double quotedPrice;
    private Integer orderedProductQuantity;
    private Double totalVolume;
    private Double totalAmount;

    private String factoryId;
    private String factoryName;
    private String linkman;
    private String contactNumber;
    private String remark;

    private Date addedDate;
    private Date lastQuotedDate;

    private Boolean syncToProduct = false;



    private String factoryProductNameEn;
    private String packageFormEn;
    private String unitEn;

    public String getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(String quotationId) {
        this.quotationId = quotationId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getFactoryProductNo() {
        return factoryProductNo;
    }

    public void setFactoryProductNo(String factoryProductNo) {
        this.factoryProductNo = factoryProductNo;
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

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
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

    public Double getFactoryPrice() {
        if (factoryPrice == null)
            return 0D;
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

    public Double getCartonVolume() {
        if (cartonVolume == null)
            return 0D;
        return cartonVolume;
    }

    public void setCartonVolume(Double cartonVolume) {
        this.cartonVolume = cartonVolume;
    }

    public Integer getPackingQuantity() {
        if (packingQuantity == null)
            return 0;
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
        if (orderedCartonQuantity == null)
            return 0;
        return orderedCartonQuantity;
    }

    public void setOrderedCartonQuantity(Integer orderedCartonQuantity) {
        this.orderedCartonQuantity = orderedCartonQuantity;
    }

    public Double getQuotedPrice() {
        if (quotedPrice == null)
            return 0D;
        return quotedPrice;
    }

    public void setQuotedPrice(Double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public Integer getOrderedProductQuantity() {
        if (orderedProductQuantity == null)
            return 0;
        return orderedProductQuantity;
    }

    public void setOrderedProductQuantity(Integer orderedProductQuantity) {
        this.orderedProductQuantity = orderedProductQuantity;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getLastQuotedDate() {
        return lastQuotedDate;
    }

    public void setLastQuotedDate(Date lastQuotedDate) {
        this.lastQuotedDate = lastQuotedDate;
    }

    public Boolean getSyncToProduct() {
        return syncToProduct;
    }

    public void setSyncToProduct(Boolean syncToProduct) {
        this.syncToProduct = syncToProduct;
    }

    public String getFactoryProductNameEn() {
        return factoryProductNameEn;
    }

    public void setFactoryProductNameEn(String factoryProductNameEn) {
        this.factoryProductNameEn = factoryProductNameEn;
    }

    public String getPackageFormEn() {
        return packageFormEn;
    }

    public void setPackageFormEn(String packageFormEn) {
        this.packageFormEn = packageFormEn;
    }

    public String getUnitEn() {
        return unitEn;
    }

    public void setUnitEn(String unitEn) {
        this.unitEn = unitEn;
    }
}
