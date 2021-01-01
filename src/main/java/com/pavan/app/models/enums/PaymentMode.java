package com.pavan.app.models.enums;

public enum PaymentMode {

    NEFT_RTGS_IMPS("NEFT/RTGS/IMPS"),
    INTERNET_BANKING("Internet Banking"),
    CARD("Card"),
    GOOGLE_PAY("GooglePay"),
    PHONE_PE("PhonePe"),
    AMAZON_PAY("Amazon Pay"),
    CASH("Cash"),
    FUND_TRANSFER("Fund Transfer"),
    PAYTM("Paytm");

    private final String mode;

    PaymentMode(String mode){
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
