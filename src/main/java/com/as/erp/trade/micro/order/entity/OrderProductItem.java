package com.as.erp.trade.micro.order.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 16-4-25.
 */
@Entity
@Table(name = "as_tb_order_product_item")
public class OrderProductItem extends BaseEntity {
    private String favorId;
    private String orderId;
    private String productId;
    private String factoryId;

    private String factoryName;
    private String linkman;
    private String contactNumber;

    private String imageURL;
    private String companyProductName;
    private String companyProductNo;
    private String factoryProductNo;
    private String functionDescription;
    private String packageForm;
    private String unit;
    private Double factoryPrice = 0D;
    private Double quotedPrice = 0D;
    private String cartonSize;
    private Integer packingQuantity = 0;
    private Double grossWeight = 0D;
    private Double netWeight = 0D;

    private Integer orderedCartonQuantity = 0;
    private Integer orderedProductQuantity = 0;
    private Double volume = 0D;
    private Double payment = 0D;
    private Integer deliveredCartonQuantity = 0;
    private Integer remainingCartonQuantity = 0;
    private Integer scheduledDeliverableCartonQuantity = 0;
    private Double scheduledDeliverableVolume = 0D;
    private Double scheduledDeliverablePayment = 0D;
    private String remark;

    private Date addedDate;

    public String getFavorId() {
        return favorId;
    }

    public void setFavorId(String favorId) {
        this.favorId = favorId;
    }

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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
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

    public String getFactoryProductNo() {
        return factoryProductNo;
    }

    public void setFactoryProductNo(String factoryProductNo) {
        this.factoryProductNo = factoryProductNo;
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
        return factoryPrice;
    }

    public void setFactoryPrice(Double factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    public Double getQuotedPrice() {
        return quotedPrice;
    }

    public void setQuotedPrice(Double quotedPrice) {
        this.quotedPrice = quotedPrice;
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

    public Integer getOrderedProductQuantity() {
        return orderedProductQuantity;
    }

    public void setOrderedProductQuantity(Integer orderedProductQuantity) {
        this.orderedProductQuantity = orderedProductQuantity;
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
