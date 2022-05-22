package com.assignment.CrossTower.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class CustomersData {

    @Id
    @Column(name = "accountNumber")
    private long accountNumber;

    @Column(name = "assetType")
    private String assetType;

    @Column(name = "assetAmount")
    private BigDecimal assetAmount;

    @CreationTimestamp
    private Date createdAt;

    @CreationTimestamp
    private Date updateAt;


    //Constructor
    public CustomersData(long accountNumber, String assetType, BigDecimal assetAmount) {

        this.accountNumber = accountNumber;
        this.assetType = assetType;
        this.assetAmount = assetAmount;
    }

    //Constructor
    public CustomersData() {
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "accountNumber=" + accountNumber +
                ", assetType='" + assetType + '\'' +
                ", assetAmount=" + assetAmount +
                '}';
    }
}
