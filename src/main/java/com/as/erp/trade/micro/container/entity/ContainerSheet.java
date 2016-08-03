package com.as.erp.trade.micro.container.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by yrx on 2016/7/26.
 */
@Entity
@Table(name = "as_tb_container_sheet")
public class ContainerSheet extends BaseEntity {
    private String favorCustomerId;
    private String customerName;
    private String currency;
    private Integer indexNumber;
//    private String shipmentPort;
//    private String unloadingPort;
//    private Date shipmentDate;
//    private String containerType;
//    private String fixedPositionNumber;
//    private String containerNumber;
//    private String sealNumber;
//    private String carrier;
    private Date addedDate;

    public String getFavorCustomerId() {
        return favorCustomerId;
    }

    public void setFavorCustomerId(String favorCustomerId) {
        this.favorCustomerId = favorCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }
//
//    public String getShipmentPort() {
//        return shipmentPort;
//    }
//
//    public void setShipmentPort(String shipmentPort) {
//        this.shipmentPort = shipmentPort;
//    }
//
//    public String getUnloadingPort() {
//        return unloadingPort;
//    }
//
//    public void setUnloadingPort(String unloadingPort) {
//        this.unloadingPort = unloadingPort;
//    }
//
//    public Date getShipmentDate() {
//        return shipmentDate;
//    }
//
//    public void setShipmentDate(Date shipmentDate) {
//        this.shipmentDate = shipmentDate;
//    }
//
//    public String getContainerType() {
//        return containerType;
//    }
//
//    public void setContainerType(String containerType) {
//        this.containerType = containerType;
//    }
//
//    public String getFixedPositionNumber() {
//        return fixedPositionNumber;
//    }
//
//    public void setFixedPositionNumber(String fixedPositionNumber) {
//        this.fixedPositionNumber = fixedPositionNumber;
//    }
//
//    public String getContainerNumber() {
//        return containerNumber;
//    }
//
//    public void setContainerNumber(String containerNumber) {
//        this.containerNumber = containerNumber;
//    }
//
//    public String getSealNumber() {
//        return sealNumber;
//    }
//
//    public void setSealNumber(String sealNumber) {
//        this.sealNumber = sealNumber;
//    }
//
//    public String getCarrier() {
//        return carrier;
//    }
//
//    public void setCarrier(String carrier) {
//        this.carrier = carrier;
//    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
