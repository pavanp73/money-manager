package com.pavan.app.services.core;

import com.pavan.app.models.dto.LookupData;
import com.pavan.app.models.dto.LookupDataDto;
import com.pavan.app.models.enums.AccountType;
import com.pavan.app.models.enums.Category;
import com.pavan.app.models.enums.PaymentMode;
import com.pavan.app.models.enums.TransactionType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LookupDataService {

    public LookupDataDto getAllLookupData() {
        return new LookupDataDto(
                getAllAccountTypes(),
                getAllTransactionTypes(),
                getAllPaymentModes(),
                getAllCategories(TransactionType.INCOME),
                getAllCategories(TransactionType.EXPENSE));
    }

    private List<LookupData> getAllAccountTypes() {
        return Arrays.stream(AccountType.values())
                .map(accountType -> new LookupData(accountType.name(), accountType.getType()))
                .collect(Collectors.toList());
    }

    private List<LookupData> getAllTransactionTypes() {
        return Arrays.stream(TransactionType.values())
                .map(transactionType -> new LookupData(transactionType.name(), transactionType.getType()))
                .collect(Collectors.toList());
    }

    private List<LookupData> getAllPaymentModes() {
        return Arrays.stream(PaymentMode.values())
                .map(paymentMode -> new LookupData(paymentMode.name(), paymentMode.getMode()))
                .collect(Collectors.toList());
    }

    private List<LookupData> getAllCategories(TransactionType transactionType) {
        return Arrays.stream(Category.values())
                .filter(category -> transactionType.name().equalsIgnoreCase(category.getTransactionType().name()))
                .map(category -> new LookupData(category.name(), category.getCategoryName()))
                .collect(Collectors.toList());
    }
}
