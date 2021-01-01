package com.pavan.app.resources.api;

import com.pavan.app.models.dto.AccountDto;
import com.pavan.app.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

@RequestMapping("accounts")
public class AccountResource {

    private final AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Operation(summary = "Add an account")
    public AccountDto addAccount(@RequestBody @Valid AccountDto accountDto){
        return accountService.addAccount(accountDto);
    }

    @GetMapping
    @Operation(summary = "List all accounts")
    public List<AccountDto> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PutMapping("/{accountId}")
    @Operation(summary = "Update an account")
    public AccountDto updateAccount(@PathVariable("accountId") String accountId,
                                    @RequestBody @Valid AccountDto accountDto){
        return accountService.updateAccount(accountId, accountDto);
    }

    @DeleteMapping("/{accountId}")
    @Operation(summary = "Delete an account")
    public String deleteAccount(@PathVariable("accountId") String accountId){
        return accountService.deleteAccount(accountId);
    }
}
