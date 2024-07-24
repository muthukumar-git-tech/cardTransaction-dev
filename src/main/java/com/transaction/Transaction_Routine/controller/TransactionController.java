package com.transaction.Transaction_Routine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.Transaction_Routine.dto.TransactionDto;
import com.transaction.Transaction_Routine.model.Transaction;
import com.transaction.Transaction_Routine.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	
	@Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto) {
        logger.info("Creating transaction for account ID: {}", transactionDto.getAccountId());
        Transaction transaction = transactionService.createTransaction(
            transactionDto.getAccountId(),
            transactionDto.getAmount(),
            transactionDto.getOperationTypeId()
        );
        logger.info("Transaction created with ID: {}", transaction.getId());
        return ResponseEntity.status(201).body(transaction);
    }

}
