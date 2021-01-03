package com.pavan.app.services.util;

import com.pavan.app.entities.Account;

public class FinUtility {

    public static double debit(double balance, double amount){
        return balance - amount;
    }

    public static double credit(double balance, double amount){
        return balance + amount;
    }
}
