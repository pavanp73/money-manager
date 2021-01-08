package com.pavan.app.models.enums;

public enum Category {

    FOOD_AND_RESTAURANT("Food & Restaurant", TransactionType.EXPENSE),
    BILLS("Bills", TransactionType.EXPENSE),
    GROCERIES("Groceries", TransactionType.EXPENSE),
    FASHION_AND_ACCESSORIES("Fashion & Accessories", TransactionType.EXPENSE),
    RENT("Rent", TransactionType.EXPENSE),
    HOUSEWARES("Housewares", TransactionType.EXPENSE),
    CAR_AND_TRANSPORT("Car & Transport", TransactionType.EXPENSE),
    FUN_AND_ENTERTAINMENT("Fun and Entertainment", TransactionType.EXPENSE),
    BEAUTY_AND_HYGIENE("Beauty & Hygiene", TransactionType.EXPENSE),
    MUTUAL_FUNDS("Mutual Funds", TransactionType.EXPENSE),
    HEALTH_AND_FITNESS("Health and Fitness", TransactionType.EXPENSE),
    HOLIDAY_AND_RELAX("Holiday & Relax", TransactionType.EXPENSE),
    PRESENTS_AND_DONATION("Presents & Donation", TransactionType.EXPENSE),

    PAYCHECK("Paycheck", TransactionType.INCOME),
    REFUNDS("Refunds", TransactionType.INCOME),
    GIFT("Gift", TransactionType.INCOME),
    PAYMENTS("Payments", TransactionType.INCOME),

    ATM_WITHDRAW("ATM Withdraw", TransactionType.TRANSFER),
    OWN_TRANSFER("Own Transfer", TransactionType.TRANSFER);

    private final String categoryName;
    private final TransactionType transactionType;

    Category(String categoryName, TransactionType transactionType){
        this.categoryName = categoryName;
        this.transactionType = transactionType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
