package com.pavan.app.entities;

import com.pavan.app.entities.base.BaseEntity;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
@Table(name = "MM_ACCOUNT")
public class Account extends BaseEntity {

    @Column(name = "account_name", length = 20, nullable = false, unique = true)
    private String accountName;

    @Column(name = "account_type", length = 20, nullable = false)
    private String accountType;

    @Column(name = "initial_amount", nullable = false)
    private Double initialAmount;

    @Column(name = "balance", nullable = false)
    private Double balance;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return  super.toString() + "Account{" +
                "accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", initialAmount=" + initialAmount +
                ", balance=" + balance +
                "} ";
    }
}
