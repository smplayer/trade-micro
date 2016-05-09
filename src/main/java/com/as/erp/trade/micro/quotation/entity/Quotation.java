package com.as.erp.trade.micro.quotation.entity;

import com.as.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 16-4-27.
 */
@Entity
@Table(name = "as_tb_quotation")
public class Quotation extends BaseEntity {

    private String customerName;
    private String region;
    private String tradeClauseType;
    private String tradeClause;
    private String shipmentPort;
    private String containerType;
    private Double profitPercent;
    private Double profitAmount;
    private Double customsClearanceFee;
    private String currency;
    private Double exchangeRate;
    private Integer decimalPlaces;
    private String editor;
    private String tel;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTradeClauseType() {
        return tradeClauseType;
    }

    public void setTradeClauseType(String tradeClauseType) {
        this.tradeClauseType = tradeClauseType;
    }

    public String getTradeClause() {
        return tradeClause;
    }

    public void setTradeClause(String tradeClause) {
        this.tradeClause = tradeClause;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public Double getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(Double profitPercent) {
        this.profitPercent = profitPercent;
    }

    public Double getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(Double profitAmount) {
        this.profitAmount = profitAmount;
    }

    public Double getCustomsClearanceFee() {
        return customsClearanceFee;
    }

    public void setCustomsClearanceFee(Double customsClearanceFee) {
        this.customsClearanceFee = customsClearanceFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
