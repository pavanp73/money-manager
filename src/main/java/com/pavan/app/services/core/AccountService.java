package com.pavan.app.services.core;

import com.pavan.app.entities.Account;
import com.pavan.app.exceptions.EntityNotFoundException;
import com.pavan.app.models.dto.AccountDto;
import com.pavan.app.repositories.AccountRepository;
import com.pavan.app.services.mapper.AccountMapper;
import com.pavan.app.services.mapper.base.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
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
        Account account = accountMapper.mapOneToEntity(accountDto);
        return accountMapper.mapOneToDto(
                accountRepository.save(account)
        );
    }

    public List<AccountDto> getAllAccounts(){
        List<AccountDto> accountDtoList = accountMapper.mapListToDto(
                accountRepository.findAll()
        );
        if(!accountDtoList.isEmpty()){
            return accountDtoList;
        }
        return new ArrayList<>();
    }

    public AccountDto updateAccount(String accountId, AccountDto accountDto){
        UUID id = UUID.fromString(accountId);
        Account accountToBeUpdated = accountRepository.findById(id).orElse(null);
        if(accountToBeUpdated == null){
            throw new EntityNotFoundException("Account was not found for {id = " + id + "}. Update failed.");
        }
        accountToBeUpdated.setAccountName(accountDto.getAccountName());
        accountToBeUpdated.setAccountType(accountDto.getAccountType());

        return accountMapper.mapOneToDto(
                accountRepository.save(accountToBeUpdated)
        );
    }

    public String deleteAccount(String accountId){
        UUID id = UUID.fromString(accountId);
        Account accountToBeDeleted = accountRepository.findById(id).orElse(null);
        if(accountToBeDeleted == null){
            throw new EntityNotFoundException("Account was not found for {id = " + id + "}. Delete failed.");
        }
        accountRepository.delete(accountToBeDeleted);

        //Todo - delete all transactions related to the account
        return "Account with " + accountId + " deleted successfully";
    }

    Account getByAccountName(String accountName){
        Account accountToBeDeleted = accountRepository.findByAccountName(accountName);
        if(accountToBeDeleted == null){
            throw new EntityNotFoundException("Account was not found for {name = " + accountName + "}.");
        }
        return accountRepository.findByAccountName(accountName);
    }

    // Update account balance(s) when transaction is deleted
    void updateAccounts(List<Account> accountList) {
        accountRepository.saveAll(accountList);
    }
}
