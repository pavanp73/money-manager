package com.pavan.app.resources.api;

import com.pavan.app.models.dto.TransactionDto;
import com.pavan.app.services.core.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionResource {

    private final TransactionService transactionService;

    @Autowired
    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Operation(summary = "Add a transaction")
    public TransactionDto addTransaction(@RequestBody @Valid TransactionDto transactionDto){
        return transactionService.addTransaction(transactionDto);
    }

    @GetMapping
    @Operation(summary = "List all transactions")
    public List<TransactionDto> getAllTransactions(){
        //Todo - filters
        return transactionService.getAllTransactions();
    }

    @PutMapping("/{transactionId}")
    @Operation(summary = "Update a transaction")
    public TransactionDto updateTransaction(@PathVariable("transactionId") String transactionId,
                                            @RequestBody @Valid TransactionDto transactionDto){
        return transactionService.updateTransaction(transactionId, transactionDto);
    }

    @DeleteMapping("/{transactionId}")
    @Operation(summary = "Delete a transaction")
    public String deleteTransaction(@PathVariable("transactionId") String transactionId){
        return transactionService.deleteTransaction(transactionId);
    }
}

