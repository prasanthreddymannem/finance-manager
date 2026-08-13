package com.prasanth.financemanager.Repo;

import com.prasanth.financemanager.Entity.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
    List<Transaction> findByUserId(Long userId);
}
