package com.pavan.app.models;

public enum TransactionType {

    EXPENSE("Expense"),
    INCOME("Income"),
    TRANSFER("Transfer");

    private final String type;

    TransactionType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}