package com.pavan.app.services.core;

import com.pavan.app.entities.Account;
import com.pavan.app.entities.Transaction;
import com.pavan.app.exceptions.EntityNotFoundException;
import com.pavan.app.exceptions.TransferAccountCannotBeEmptyException;
import com.pavan.app.models.dto.TransactionDto;
import com.pavan.app.models.enums.TransactionType;
import com.pavan.app.repositories.TransactionRepository;
import com.pavan.app.services.mapper.TransactionMapper;
import com.pavan.app.services.util.FinUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
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
        log.info("Creating a transaction");
        Transaction transaction = createTransactionEntityForAddAndUpdate(transactionDto);

        transaction = transactionRepository.save(transaction);
        log.info("Transaction created with id: {}", transaction.getId());

        return transactionMapper.mapOneToDto(transaction);
    }

    public List<TransactionDto> getAllTransactions(){
        List<TransactionDto> transactionDtoList = transactionMapper.mapListToDto(
                transactionRepository.findAll()
        );
        if(!transactionDtoList.isEmpty()){
            log.info("Total number of transactions: {}", transactionDtoList.size());
            return transactionDtoList;
        }
        log.info("No transactions found");
        return new ArrayList<>();
    }

    public TransactionDto updateTransaction(String transactionId, TransactionDto transactionDto){
        UUID id = UUID.fromString(transactionId);
        log.info("Transaction to be updated: {}", id);
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            log.error("Transaction was not found for {id = {}}. Update failed.", id);
            throw new EntityNotFoundException("Transaction was not found for {id = " + id + "}. Update failed.");
        }
        log.info("Restoring account balances before update");
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

        transaction = transactionRepository.save(transaction);
        log.info("Transaction with id: {} updated successfully", id);

        return transactionMapper.mapOneToDto(transaction);
    }

    public String deleteTransaction(String transactionId){
        UUID id = UUID.fromString(transactionId);
        log.info("Transaction to be deleted: {}", id);
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            log.error("Transaction was not found for {id = {}}. Delete failed.", id);
            throw new EntityNotFoundException("Transaction was not found for {id = " + id + "}. Delete failed.");
        }
        //delete transaction
        transactionRepository.delete(transaction);
        log.info("Transaction with id: {} deleted successfully", id);

        //update account balance
        log.info("Restoring account balances after deleting transaction");
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
            validateTransferAccountForTransferType(transactionDto.getTransferToAccount());
            Account transferToAccount = accountService.getByAccountName(transactionDto.getTransferToAccount());
            account.setBalance(FinUtility.debit(account.getBalance(), transactionDto.getAmount()));
            transferToAccount.setBalance(FinUtility.credit(transferToAccount.getBalance(), transactionDto.getAmount()));
            transaction.setTransferToAccount(transferToAccount);
        }
        transaction.setAccount(account);
        return transaction;
    }

    private void validateTransferAccountForTransferType(String transferToAccount) {
        if(transferToAccount == null || transferToAccount.isEmpty()){
            log.error("Transfer account : {} cannot be empty for TRANSFER transaction type", transferToAccount);
            throw new TransferAccountCannotBeEmptyException("Transfer account cannot be empty for TRANSFER transaction type");
        }
    }
}
