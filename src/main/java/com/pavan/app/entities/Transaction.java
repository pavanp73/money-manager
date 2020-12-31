package com.pavan.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pavan.app.entities.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "MM_TRANSACTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction extends BaseEntity {

    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account", nullable = false)
    private Account fromAccount;

    @Column(name = "category", length = 25, nullable = false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account")
    private Account toAccount;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "note", length = 200)
    private String note;

    @Column(name = "payment_mode", length = 25, nullable = false)
    private String paymentMode;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    @Override
    public String toString() {
        return super.toString() + "Transaction{" +
                "transactionType='" + transactionType + '\'' +
                ", fromAccount=" + fromAccount +
                ", category='" + category + '\'' +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                "} ";
    }
}
