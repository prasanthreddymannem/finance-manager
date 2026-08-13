package com.prasanth.financemanager.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanth.financemanager.Entity.Transaction;
import com.prasanth.financemanager.Service.TransactionService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;
    }
    @GetMapping
    public List<Transaction> getTransactions(Authentication authentication){
        String email=authentication.getName();
        return transactionService.getUserTransactions(email);
    }
    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody Transaction transaction,Authentication authentication) {   
        
        String email=authentication.getName();
        return transactionService.createTransaction(transaction,email);
    }
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id,Authentication authentication){
        String email=authentication.getName();
        transactionService.deleteTransaction(id,email);
    }
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id,@RequestBody Transaction transaction,Authentication authentication) {
        String email=authentication.getName();
        return transactionService.updateTransactionService(id,transaction,email);
    }
}
