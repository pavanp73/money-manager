package com.pavan.app.services.core;

import com.pavan.app.entities.Account;
import com.pavan.app.models.dto.AccountDto;
import com.pavan.app.repositories.AccountRepository;
import com.pavan.app.services.mapper.AccountMapper;
import com.pavan.app.services.mapper.base.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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
        return null;
    }

    public AccountDto updateAccount(String accountId, AccountDto accountDto){
        UUID id = UUID.fromString(accountId);
        Account accountToBeUpdated = accountRepository.findById(id).orElse(null);
        if(accountToBeUpdated == null){
            return null;
        }
        accountToBeUpdated.setAccountName(accountDto.getAccountName());
        accountToBeUpdated.setAccountType(accountDto.getAccountType());

        //For now only Account Name and Account Type can ne updated
        //Todo - update initial amount
        return accountMapper.mapOneToDto(
                accountRepository.save(accountToBeUpdated)
        );
    }

    public String deleteAccount(String accountId){
        accountRepository.deleteById(UUID.fromString(accountId));

        //Todo - delete all transactions related to the account
        return "Account with " + accountId + " deleted successfully";
    }

    public Account getByAccountName(String accountName){
        return accountRepository.findByAccountName(accountName);
    }
}
