package com.pavan.app.services.core;

import com.pavan.app.entities.Account;
import com.pavan.app.entities.Transaction;
import com.pavan.app.models.dto.TransactionDto;
import com.pavan.app.models.enums.OperationType;
import com.pavan.app.models.enums.TransactionType;
import com.pavan.app.repositories.TransactionRepository;
import com.pavan.app.services.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Transaction transaction = transactionMapper.mapOneToEntity(transactionDto);
        Account fromAccount = accountService.getByAccountName(transactionDto.getAccount());
        transaction.setAccount(fromAccount);
        if(transactionDto.getTransactionType().equalsIgnoreCase(TransactionType.TRANSFER.name())){
            //Todo - throw exception if destination account name is empty
            Account toAccount = accountService.getByAccountName(transactionDto.getTransferToAccount());
            transaction.setTransferToAccount(toAccount);
        }
        Transaction savedTransaction = transactionRepository.save(transaction);
        //update account's balance as per the transaction type
        accountService.updateBalance(savedTransaction, OperationType.ADD_OR_UPDATE);
        return transactionMapper.mapOneToDto(savedTransaction);
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
        Transaction transactionToBeUpdated = transactionMapper.mapOneToEntity(transactionDto);
        transactionToBeUpdated.setId(id);
        Transaction updatedTransaction = transactionRepository.save(transactionToBeUpdated);
        //if there is any change in amount then update account's balance
        if(!transactionToBeUpdated.getAmount().equals(updatedTransaction.getAmount())){
            accountService.updateBalance(updatedTransaction, OperationType.ADD_OR_UPDATE);
        }
        return transactionMapper.mapOneToDto(updatedTransaction);
    }

    public String deleteTransaction(String transactionId){
        UUID id = UUID.fromString(transactionId);
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            return null;
        }
        transactionRepository.delete(transaction);
        accountService.updateBalance(transaction, OperationType.DELETE);
        return "Transaction with " + transactionId + " deleted successfully";
    }
}
