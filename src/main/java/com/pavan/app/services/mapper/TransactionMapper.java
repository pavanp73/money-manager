package com.pavan.app.services.mapper;

import com.pavan.app.entities.Transaction;
import com.pavan.app.models.dto.TransactionDto;
import com.pavan.app.services.mapper.base.AbstractMapper;
import com.pavan.app.services.util.DateUtility;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionMapper extends AbstractMapper<Transaction, TransactionDto> {

    @Override
    protected Transaction mapToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setCategory(transactionDto.getCategory());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setNote(transactionDto.getNote());
        transaction.setPaymentMode(transactionDto.getPaymentMode());
        transaction.setTransactionDate(
                DateUtility.convertToDate(transactionDto.getTransactionDate()));
        return transaction;
    }

    @Override
    protected TransactionDto mapToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId().toString());
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setFromAccountName(transaction.getFromAccount().getAccountName());
        transactionDto.setCategory(transaction.getCategory());
        if(transaction.getToAccount() != null){
            transactionDto.setToAccountName(transaction.getToAccount().getAccountName());
        }
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setNote(transaction.getNote());
        transactionDto.setPaymentMode(transaction.getPaymentMode());
        transactionDto.setTransactionDate(transaction.getTransactionDate().toString());
        return transactionDto;
    }
}