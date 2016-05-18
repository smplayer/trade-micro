package com.as.erp.trade.micro.quotation.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 16-4-27.
 */
@Entity
@Table(name = "as_tb_quotation_product_item")
public class QuotationProductItem extends BaseEntity {

    private String quotationId;
    private String productId;
//    private String factoryProductName;
//    private String factoryProductNo;
//    private String companyProductName;
    private Double quotedPrice;
    private Integer orderedCartonQuantity;
    private Integer orderedProductQuantity;
    private Double totalVolume;
    private Double totalAmount;

    private String factoryId;
//    private String factoryName;
//    private String linkman;
//    private String contactNumber;
    private String remark;

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

//    public String getFactoryProductName() {
//        return factoryProductName;
//    }
//
//    public void setFactoryProductName(String factoryProductName) {
//        this.factoryProductName = factoryProductName;
//    }
//
//    public String getCompanyProductName() {
//        return companyProductName;
//    }
//
//    public void setCompanyProductName(String companyProductName) {
//        this.companyProductName = companyProductName;
//    }
//
//    public String getFactoryProductNo() {
//        return factoryProductNo;
//    }
//
//    public void setFactoryProductNo(String factoryProductNo) {
//        this.factoryProductNo = factoryProductNo;
//    }

    public Double getQuotedPrice() {
        return quotedPrice;
    }

    public void setQuotedPrice(Double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public Integer getOrderedCartonQuantity() {
        return orderedCartonQuantity;
    }

    public void setOrderedCartonQuantity(Integer orderedCartonQuantity) {
        this.orderedCartonQuantity = orderedCartonQuantity;
    }

    public Integer getOrderedProductQuantity() {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
