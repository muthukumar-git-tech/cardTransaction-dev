package com.transaction.Transaction_Routine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.Transaction_Routine.event.AccountEvent;
import com.transaction.Transaction_Routine.event.EventPublisher;
import com.transaction.Transaction_Routine.model.Account;
import com.transaction.Transaction_Routine.repository.AccountRepository;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	 @Autowired
	  private AccountRepository accountRepository;
	 
	 @Autowired
	  private EventPublisher eventPublisher;
	 
		public Account createAccount(String documentNumber) {
	        logger.info("Creating account for document number: {}", documentNumber);
			Account account = new Account();
			account.setDocumentNumber(documentNumber);
			account = accountRepository.save(account);
	        logger.info("Account created with ID: {}", account.getId());

			AccountEvent event = new AccountEvent();
			event.setAccountId(account.getId());
			event.setDocumentNumber(documentNumber);
			eventPublisher.publishAccountEvent(event);

			return account;
		}

		public Account getAccount(Long accountId) {
			logger.info("Fetching account with ID: {}", accountId);
			return accountRepository.findById(accountId).orElseThrow(() -> {
			logger.error("Account not found with ID: {}", accountId);
			return new RuntimeException("Account not found with id: " + accountId);
			});
		}

}
