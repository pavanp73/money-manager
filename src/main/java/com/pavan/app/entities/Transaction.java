package com.pavan.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pavan.app.entities.base.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MM_TRANSACTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction extends BaseEntity {

    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    @Column(name = "category", length = 25, nullable = false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_to_account")
    private Account transferToAccount;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "note", length = 200)
    private String note;

    @Column(name = "payment_mode", length = 25, nullable = false)
    private String paymentMode;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Account getTransferToAccount() {
        return transferToAccount;
    }

    public void setTransferToAccount(Account transferToAccount) {
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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return super.toString() + "Transaction{" +
                "transactionType='" + transactionType + '\'' +
                ",account=" + account +
                ", category='" + category + '\'' +
                ", transferToAccount=" + transferToAccount +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                "} ";
    }
}
