package com.transaction.Transaction_Routine.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.Transaction_Routine.event.EventPublisher;
import com.transaction.Transaction_Routine.event.TransactionEvent;
import com.transaction.Transaction_Routine.model.Account;
import com.transaction.Transaction_Routine.model.Transaction;
import com.transaction.Transaction_Routine.model.TransactionType;
import com.transaction.Transaction_Routine.repository.AccountRepository;
import com.transaction.Transaction_Routine.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EventPublisher eventPublisher;

    @Transactional
    public Transaction createTransaction(Long accountId, BigDecimal amount, int operationTypeId) {
        logger.info("Creating transaction for account ID: {} with amount: {}", accountId, amount);
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));

        TransactionType type = TransactionType.fromId(operationTypeId);
        if (type == TransactionType.PURCHASE || type == TransactionType.WITHDRAWAL) {
            amount = amount.negate();
        }

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        TransactionEvent event = new TransactionEvent();
        event.setTransactionId(transaction.getId());
        event.setAccountId(account.getId());
        event.setAmount(amount);
        event.setTransactionType(type.name());
        eventPublisher.publishTransactionEvent(event);
        logger.info("Transaction created with ID: {}", transaction.getId());

        return transaction;
    }

}
