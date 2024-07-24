package com.transaction.Transaction_Routine.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.Transaction_Routine.model.Account;
import com.transaction.Transaction_Routine.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
 private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	
	@Autowired
    private AccountService accountService;
	
	@PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, String> request) {
        String documentNumber = request.get("document_number");
        logger.info("Creating account for document number: {}", documentNumber);
        Account account = accountService.createAccount(documentNumber);
        logger.info("Account created with ID: {}", account.getId());
        return ResponseEntity.status(201).body(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
    	logger.info("Fetching account with ID: {}", accountId);
        Account account = accountService.getAccount(accountId);
        logger.info("Fetched account: {}", account);
        return ResponseEntity.ok(account);
    }

}
