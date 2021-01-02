package com.pavan.app.services.util;

import com.pavan.app.entities.Account;

public class FinUtility {

    public static double debit(Account account, double amount){
        return account.getBalance() - amount;
    }

    public static double credit(Account account, double amount){
        return account.getBalance() + amount;
    }
}
