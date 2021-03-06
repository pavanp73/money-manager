package com.pavan.app.models.dto;

import java.util.List;

public class LookupDataDto {

    private final List<LookupData> accountTypes;
    private final List<LookupData> transactionTypes;
    private final List<LookupData> paymentModes;
    private final List<LookupData> incomeCategories;
    private final List<LookupData> expenseCategories;
    private final List<LookupData> transferCategories;

    public LookupDataDto(List<LookupData> accountTypes,
                         List<LookupData> transactionTypes,
                         List<LookupData> paymentModes,
                         List<LookupData> incomeCategories,
                         List<LookupData> expenseCategories,
                         List<LookupData> transferCategories) {
        this.accountTypes = accountTypes;
        this.transactionTypes = transactionTypes;
        this.paymentModes = paymentModes;
        this.incomeCategories = incomeCategories;
        this.expenseCategories = expenseCategories;
        this.transferCategories = transferCategories;
    }

    public List<LookupData> getAccountTypes() {
        return accountTypes;
    }

    public List<LookupData> getTransactionTypes() {
        return transactionTypes;
    }

    public List<LookupData> getPaymentModes() {
        return paymentModes;
    }

    public List<LookupData> getIncomeCategories() {
        return incomeCategories;
    }

    public List<LookupData> getExpenseCategories() {
        return expenseCategories;
    }

    public List<LookupData> getTransferCategories() {
        return transferCategories;
    }

    @Override
    public String toString() {
        return "LookupDataDto{" +
                "accountTypes=" + accountTypes +
                ", transactionTypes=" + transactionTypes +
                ", paymentModes=" + paymentModes +
                ", incomeCategories=" + incomeCategories +
                ", expenseCategories=" + expenseCategories +
                ", transferCategories=" + transferCategories +
                '}';
    }
}
