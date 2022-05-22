package com.assignment.CrossTower.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeModel {

    private long fromAccNbr;
    private String fromAssetType;
    private BigDecimal fromAssetAmount;
    private long toAccNbr;
    private String toAssetType;
    private BigDecimal toAssetAmount;

    //Constructor
    public ExchangeModel(long fromAccNbr, String fromAssetType, BigDecimal fromAssetAmount, long toAccNbr, String toAssetType, BigDecimal toAssetAmount) {

        this.fromAccNbr = fromAccNbr;
        this.fromAssetType = fromAssetType;
        this.fromAssetAmount = fromAssetAmount;
        this.toAccNbr = toAccNbr;
        this.toAssetType = toAssetType;
        this.toAssetAmount = toAssetAmount;
    }

    public ExchangeModel() {
    }

    @Override
    public String toString() {
        return "ExchangeModel{" +
                "fromAccNbr=" + fromAccNbr +
                ", fromAssetType='" + fromAssetType + '\'' +
                ", fromAssetAmount=" + fromAssetAmount +
                ", toAccNbr=" + toAccNbr +
                ", toAssetType='" + toAssetType + '\'' +
                ", toAssetAmount=" + toAssetAmount +
                '}';
    }
}
