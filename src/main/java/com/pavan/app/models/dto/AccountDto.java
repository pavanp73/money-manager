package com.pavan.app.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pavan.app.annotations.CheckAccountType;
import com.pavan.app.annotations.NotNullOrBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto {

    private String id;

    @NotNullOrBlank(message = "Account name cannot be empty")
    private String accountName;

    @NotNullOrBlank(message = "Account type cannot be empty")
    @CheckAccountType(message = "Account type must be either " +
            "Bank/Debit Card/Credit Card/Wallet/Savings")
    private String accountType;

    private Double initialAmount;

    private Double balance;

    public AccountDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        return "AccountRequestDto{" +
                "id='" + id + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", initialAmount=" + initialAmount +
                ", balance=" + balance +
                '}';
    }
}
