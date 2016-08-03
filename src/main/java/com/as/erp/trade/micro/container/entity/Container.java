package com.as.erp.trade.micro.container.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 16-4-26.
 */
@Entity
@Table(name = "as_tb_container")
public class Container extends BaseEntity {
    //容量
    private Double containerVolume;
    //装运港
    private String shipmentPort;
    //目的港
    private String destinationPort;
    //装运日期
    private String deliveryDate;
    //柜型
    private String containerType;
    //柜号
    private String containerNo;
    //订舱号
    private String cabinNo;
    //封条号
    private String sealNo;
    //承运人
    private String carrier;

    //favor index
    private Integer indexNumber;

    private String sheetId;

    public Double getContainerVolume() {
        return containerVolume != null ? containerVolume : 0D;
    }

    public void setContainerVolume(Double containerVolume) {
        this.containerVolume = containerVolume;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getCabinNo() {
        return cabinNo;
    }

    public void setCabinNo(String cabinNo) {
        this.cabinNo = cabinNo;
    }

    public String getSealNo() {
        return sealNo;
    }

    public void setSealNo(String sealNo) {
        this.sealNo = sealNo;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }
}
