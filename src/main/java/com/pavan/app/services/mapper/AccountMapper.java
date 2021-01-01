package com.pavan.app.services.mapper;

import com.pavan.app.entities.Account;
import com.pavan.app.models.dto.AccountDto;
import com.pavan.app.services.mapper.base.AbstractMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper extends AbstractMapper<Account, AccountDto> {

    @Override
    protected Account mapToEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountName(accountDto.getAccountName());
        account.setAccountType(accountDto.getAccountType());
        account.setInitialAmount(accountDto.getInitialAmount());
        account.setBalance(accountDto.getInitialAmount());
        return account;
    }

    @Override
    protected AccountDto mapToDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId().toString());
        accountDto.setAccountName(account.getAccountName());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setInitialAmount(account.getInitialAmount());
        accountDto.setBalance(account.getBalance());
        return accountDto;
    }
}
