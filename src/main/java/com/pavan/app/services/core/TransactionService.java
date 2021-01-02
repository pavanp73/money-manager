package com.pavan.app.services.core;

import com.pavan.app.entities.Account;
import com.pavan.app.entities.Transaction;
import com.pavan.app.models.dto.TransactionDto;
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
        Account fromAccount = accountService.getByAccountName(transactionDto.getFromAccountName());
        transaction.setFromAccount(fromAccount);
        //Todo - get account from it's name and set source account
        //Todo - and set destination account only transaction type is TRANSFER
        return transactionMapper.mapOneToDto(
                transactionRepository.save(transaction)
        );
        //Todo - update source account's balance based on transaction type
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
        return transactionMapper.mapOneToDto(
                transactionRepository.save(transactionToBeUpdated)
        );
        //Todo - update source account's balance based on transaction type
    }

    public String deleteTransaction(String transactionId){
        transactionRepository.deleteById(UUID.fromString(transactionId));
        return "Transaction with " + transactionId + " deleted successfully";
        //Todo - update source account's balance based on transaction type
    }
}
