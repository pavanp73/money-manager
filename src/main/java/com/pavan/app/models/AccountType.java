package com.pavan.app.models;

public enum AccountType {

    BANK("Bank"),
    DEBIT_CARD("Debit Card"),
    CREDIT_CARD("Credit Card"),
    WALLET("Wallet"),
    SAVINGS("Savings");

    private final String type;

    AccountType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
