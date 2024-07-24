package com.transaction.Transaction_Routine.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.transaction.Transaction_Routine.model.Account;
import com.transaction.Transaction_Routine.model.Transaction;
import com.transaction.Transaction_Routine.model.TransactionType;
import com.transaction.Transaction_Routine.repository.AccountRepository;
import com.transaction.Transaction_Routine.repository.TransactionRepository;
import com.transaction.Transaction_Routine.service.AccountService;
import com.transaction.Transaction_Routine.service.TransactionService;

@SpringBootTest
@ActiveProfiles("test")
class TransactionRoutineApplicationTests {
	
    private static final Logger logger = LoggerFactory.getLogger(TransactionRoutineApplicationTests.class);

	
	@Mock
    private AccountRepository accountRepository;
	
	@Mock
    private TransactionRepository transactionRepository;
	
	@InjectMocks
    @Autowired
    private AccountService accountService;

    @InjectMocks
    @Autowired
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	void contextLoads() {
	}
	
	@Test
    void testCreateAccount() {
		logger.info("Starting testCreateAccount...");
        Account account = new Account();
        account.setId(1L);
        account.setDocumentNumber("12345678900");

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount("12345678900");
        logger.info("Created account with ID: {}", createdAccount.getId());

        assertEquals("12345678900", createdAccount.getDocumentNumber());
        assertEquals(1L, createdAccount.getId());
        verify(accountRepository, times(1)).save(any(Account.class));
        logger.info("testCreateAccount completed successfully.");

    }

    @Test
    void testGetAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setDocumentNumber("12345678900");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account fetchedAccount = accountService.getAccount(1L);

        assertEquals("12345678900", fetchedAccount.getDocumentNumber());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateTransaction() {
        Account account = new Account();
        account.setId(1L);
        account.setDocumentNumber("12345678900");
        account.setBalance(BigDecimal.ZERO);

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(new BigDecimal("123.45"));
        transaction.setType(TransactionType.PURCHASE_INSTALLMENTS);
        transaction.setAccount(account);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Transaction createdTransaction = transactionService.createTransaction(1L, new BigDecimal("123.45"), 4);

        assertEquals(new BigDecimal("123.45"), createdTransaction.getAmount());
        assertEquals(TransactionType.PURCHASE_INSTALLMENTS, createdTransaction.getType());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

}
