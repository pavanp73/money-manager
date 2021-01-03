package com.pavan.app.services.core;

import com.pavan.app.entities.Account;
import com.pavan.app.entities.Transaction;
import com.pavan.app.models.dto.TransactionDto;
import com.pavan.app.models.enums.TransactionType;
import com.pavan.app.repositories.TransactionRepository;
import com.pavan.app.services.mapper.TransactionMapper;
import com.pavan.app.services.util.FinUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              TransactionMapper transactionMapper,
                              AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountService = accountService;
    }

    public TransactionDto addTransaction(TransactionDto transactionDto){
        Transaction transaction = createTransactionEntityForAddAndUpdate(transactionDto);
        return transactionMapper.mapOneToDto(
                transactionRepository.save(transaction));
    }

    public List<TransactionDto> getAllTransactions(){
        List<TransactionDto> transactionDtoList = transactionMapper.mapListToDto(
                transactionRepository.findAll()
        );
        if(!transactionDtoList.isEmpty()){
            return transactionDtoList;
        }
        return null;
    }

    public TransactionDto updateTransaction(String transactionId, TransactionDto transactionDto){
        UUID id = UUID.fromString(transactionId);
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            return null;
        }
        restoreAccountBalance(transaction);

        // update transaction
        Transaction updatedTransaction = createTransactionEntityForAddAndUpdate(transactionDto);
        transaction.setAccount(updatedTransaction.getAccount());
        transaction.setTransferToAccount(updatedTransaction.getTransferToAccount());
        transaction.setTransactionType(updatedTransaction.getTransactionType());
        transaction.setTransactionDate(updatedTransaction.getTransactionDate());
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setCategory(updatedTransaction.getCategory());
        transaction.setNote(updatedTransaction.getNote());
        transaction.setPaymentMode(updatedTransaction.getPaymentMode());
        return transactionMapper.mapOneToDto(
                transactionRepository.save(transaction)
        );
    }

    public String deleteTransaction(String transactionId){
        UUID id = UUID.fromString(transactionId);
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            return null;
        }
        //delete transaction
        transactionRepository.delete(transaction);
        //update account balance
        restoreAccountBalance(transaction);
        return "Transaction with " + transactionId + " deleted successfully";
    }


    private void restoreAccountBalance(Transaction transaction) {
        List<Account> accountList = new ArrayList<>();
        Account account = transaction.getAccount();
        if(transaction.getTransactionType().equalsIgnoreCase(TransactionType.EXPENSE.name())){
            account.setBalance(FinUtility.credit(account.getBalance(), transaction.getAmount()));
        }
        else if(transaction.getTransactionType().equalsIgnoreCase(TransactionType.INCOME.name())){
            account.setBalance(FinUtility.debit(account.getBalance(), transaction.getAmount()));
        }
        else{
            // TRANSFER transaction type
            Account transferToAccount = transaction.getTransferToAccount();
            account.setBalance(FinUtility.credit(account.getBalance(), transaction.getAmount()));
            transferToAccount.setBalance(FinUtility.debit(transferToAccount.getBalance(), transaction.getAmount()));
            accountList.add(transferToAccount);
        }
        accountList.add(account);
        accountService.updateAccounts(accountList);
    }

    private Transaction createTransactionEntityForAddAndUpdate(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.mapOneToEntity(transactionDto);
        Account account = accountService.getByAccountName(transactionDto.getAccount());

        if(transactionDto.getTransactionType().equalsIgnoreCase(TransactionType.EXPENSE.name())){
            account.setBalance(FinUtility.debit(account.getBalance(), transactionDto.getAmount()));
        }
        else if(transactionDto.getTransactionType().equalsIgnoreCase(TransactionType.INCOME.name())){
            account.setBalance(FinUtility.credit(account.getBalance(), transactionDto.getAmount()));
        }
        else{
            // TRANSFER transaction type
            //Todo - throw exception if destination account name is empty
            Account transferToAccount = accountService.getByAccountName(transactionDto.getTransferToAccount());
            account.setBalance(FinUtility.debit(account.getBalance(), transactionDto.getAmount()));
            transferToAccount.setBalance(FinUtility.credit(transferToAccount.getBalance(), transactionDto.getAmount()));
            transaction.setTransferToAccount(transferToAccount);
        }
        transaction.setAccount(account);
        return transaction;
    }
}
