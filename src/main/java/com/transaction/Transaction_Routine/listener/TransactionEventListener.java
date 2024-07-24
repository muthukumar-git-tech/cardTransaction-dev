package com.transaction.Transaction_Routine.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.transaction.Transaction_Routine.event.TransactionEvent;
import com.transaction.Transaction_Routine.model.Account;
import com.transaction.Transaction_Routine.model.TransactionType;
import com.transaction.Transaction_Routine.repository.AccountRepository;

@Component
public class TransactionEventListener {
	
    private static final Logger logger = LoggerFactory.getLogger(TransactionEventListener.class);
    
    @Autowired
    private AccountRepository accountRepository;
	
	@EventListener
    public void handleTransactionCreatedEvent(TransactionEvent event) {
        logger.info("Received TransactionCreatedEvent - Transaction ID: {}, Account ID: {}, Amount: {}, Type: {}",
                event.getTransactionId(), event.getAccountId(), event.getAmount(), event.getTransactionType());
        processEvent(event);
        System.out.println("Received transaction created event: " + event);
    }
	
	private void processEvent(TransactionEvent event) {
        logger.info("Processing transaction event for Transaction ID: {}", event.getTransactionId());

        if ("CREDIT_VOUCHER".equals(event.getTransactionType())) {
            logger.info("Processing a credit voucher transaction.");
        }
        updateAccountBalance(event);

       logger.info("Transaction processing completed for Transaction ID: {}", event.getTransactionId());

    }
	
	private void updateAccountBalance(TransactionEvent event) {
        Account account = accountRepository.findById(event.getAccountId()).orElse(null);
        if (account == null) {
            logger.error("Account not found for ID: {}", event.getAccountId());
            return;
        }
        if (TransactionType.PURCHASE.name().equals(event.getTransactionType()) ||
                TransactionType.WITHDRAWAL.name().equals(event.getTransactionType())) {
                account.setBalance(account.getBalance().subtract(event.getAmount()));
            } else if (TransactionType.CREDIT_VOUCHER.name().equals(event.getTransactionType())) {
                account.setBalance(account.getBalance().add(event.getAmount()));
            } else if (TransactionType.PURCHASE_INSTALLMENTS.name().equals(event.getTransactionType())) {
                account.setBalance(account.getBalance().subtract(event.getAmount()));
            } else {
                logger.warn("Unhandled transaction type: {}", event.getTransactionType());
            }

            // Save the updated account
            accountRepository.save(account);
            logger.info("Updated account balance for Account ID: {}", account.getId());
        }

}
