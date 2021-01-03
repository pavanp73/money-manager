package com.pavan.app.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pavan.app.annotations.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {

    private String id;

    @NotNullOrBlank(message = "Transaction type cannot be empty")
    @CheckTransactionType(message = "Invalid transaction type")
    private String transactionType;

    @NotNullOrBlank(message = "Source Account name cannot be empty")
    private String account;

    @NotNullOrBlank(message = "Category cannot be empty")
    @CheckCategory(message = "Invalid category")
    private String category;

    private String transferToAccount;

    @NotZero(message = "Amount cannot be 0/0.0")
    private Double amount;

    private String note;

    @NotNullOrBlank(message = "Payment mode cannot be empty")
    @CheckPaymentMode(message = "Invalid payment mode")
    private String paymentMode;

    @DateFormatCheck(message = "Transaction date cannot be empty. Date format; dd-MM-yyyy")
    private String transactionDate;

    public TransactionDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransferToAccount() {
        return transferToAccount;
    }

    public void setTransferToAccount(String transferToAccount) {
        this.transferToAccount = transferToAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id='" + id + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", sourceAccountName='" + account + '\'' +
                ", category='" + category + '\'' +
                ", destinationAccountName='" + transferToAccount + '\'' +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }
}
