package com.transaction.Transaction_Routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.Transaction_Routine.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
