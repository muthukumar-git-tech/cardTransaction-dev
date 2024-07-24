package com.transaction.Transaction_Routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.Transaction_Routine.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
