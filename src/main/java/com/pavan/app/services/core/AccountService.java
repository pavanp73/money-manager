package com.pavan.app.services.core;

import com.pavan.app.entities.Account;
import com.pavan.app.exceptions.EntityNotFoundException;
import com.pavan.app.models.dto.AccountDto;
import com.pavan.app.repositories.AccountRepository;
import com.pavan.app.services.mapper.AccountMapper;
import com.pavan.app.services.mapper.base.AbstractMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final AbstractMapper<Account, AccountDto> accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDto addAccount(AccountDto accountDto){
        log.info("Creating an account");
        Account account = accountMapper.mapOneToEntity(accountDto);

        account = accountRepository.save(account);
        log.info("Account created with id: {} with name: {}", account.getId(), account.getAccountName());

        return accountMapper.mapOneToDto(account);
    }

    public List<AccountDto> getAllAccounts(){
        List<AccountDto> accountDtoList = accountMapper.mapListToDto(
                accountRepository.findAll()
        );
        if(!accountDtoList.isEmpty()){
            log.info("Total number of accounts: {}", accountDtoList.size());
            return accountDtoList;
        }
        log.info("No accounts found");
        return new ArrayList<>();
    }

    public AccountDto updateAccount(String accountId, AccountDto accountDto){
        UUID id = UUID.fromString(accountId);
        log.info("Account to be updated: {}", id);
        Account accountToBeUpdated = accountRepository.findById(id).orElse(null);
        if(accountToBeUpdated == null){
            log.error("Account was not found for {id = {}}. Update failed.", id);
            throw new EntityNotFoundException("Account was not found for {id = " + id + "}. Update failed.");
        }
        accountToBeUpdated.setAccountName(accountDto.getAccountName());
        accountToBeUpdated.setAccountType(accountDto.getAccountType());

        accountToBeUpdated = accountRepository.save(accountToBeUpdated);
        log.info("Account with id: {} updated successfully", id);
        return accountMapper.mapOneToDto(accountToBeUpdated);
    }

    public String deleteAccount(String accountId){
        UUID id = UUID.fromString(accountId);
        log.info("Account to be deleted: {}", id);
        Account accountToBeDeleted = accountRepository.findById(id).orElse(null);
        if(accountToBeDeleted == null){
            log.error("Account was not found for {id = {}}. Delete failed.", id);
            throw new EntityNotFoundException("Account was not found for {id = " + id + "}. Delete failed.");
        }
        accountRepository.delete(accountToBeDeleted);
        log.info("Account with id: {} deleted successfully", id);
        //Todo - delete all transactions related to the account
        return "Account with " + accountId + " deleted successfully";
    }

    Account getByAccountName(String accountName){
        Account accountToBeDeleted = accountRepository.findByAccountName(accountName);
        if(accountToBeDeleted == null){
            log.error("Account was not found for {name = {}}. Delete failed.", accountName);
            throw new EntityNotFoundException("Account was not found for {name = " + accountName + "}.");
        }
        return accountRepository.findByAccountName(accountName);
    }

    // Update account balance(s) when transaction is deleted
    void updateAccounts(List<Account> accountList) {
        accountRepository.saveAll(accountList);
    }
}
