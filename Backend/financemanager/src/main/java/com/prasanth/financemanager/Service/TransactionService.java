package com.prasanth.financemanager.Service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.prasanth.financemanager.Entity.Transaction;
import com.prasanth.financemanager.Entity.User;
import com.prasanth.financemanager.Repo.TransactionRepository;
import com.prasanth.financemanager.Repo.UserRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    public TransactionService(TransactionRepository transactionRepository,UserRepository userRepository){
        this.transactionRepository=transactionRepository;
        this.userRepository=userRepository;
    }
    public List<Transaction> getUserTransactions(String email){
        User user=userRepository.findByEmail(email)
        .orElseThrow(()->new RuntimeException("User Not Found") );
        return transactionRepository.findByUserId(user.getId());
    }
    public Transaction createTransaction(Transaction transaction,String email){
        User user=userRepository.findByEmail(email).orElseThrow(
            ()->new RuntimeException("User not found")
        );
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }
    public void deleteTransaction(Long id,String email){
        User user=userRepository.findByEmail(email)
        .orElseThrow(()->new RuntimeException("User not Found"));

        Transaction transaction=transactionRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Transaction not Found"));

        if(!transaction.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Not authorized");
        }
        transactionRepository.delete(transaction);
    }
    public Transaction updateTransactionService(Long id,Transaction updatedTransaction,String email){
        User user=userRepository.findByEmail(email)
        .orElseThrow(()->new RuntimeException("User not found"));

        Transaction exisitingTransaction=transactionRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Transaction not Found"));

        if(!exisitingTransaction.getUser().getId().equals(user.getId())){
            throw new RuntimeException("You are Not authorized");
        }

        exisitingTransaction.setAmount(updatedTransaction.getAmount());
        exisitingTransaction.setType(updatedTransaction.getType());
        exisitingTransaction.setCategory(updatedTransaction.getCategory());
        exisitingTransaction.setDescription(updatedTransaction.getDescription());
        exisitingTransaction.setDate(updatedTransaction.getDate());

        return transactionRepository.save(exisitingTransaction);
    }
}
