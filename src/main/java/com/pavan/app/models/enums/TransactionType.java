package com.pavan.app.models.enums;

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

    public static TransactionType of(String value){
        for(TransactionType e : TransactionType.values()){
            if(e.getType().equals(value))
                return e;
        }
        throw new IllegalArgumentException(value);
    }
}
