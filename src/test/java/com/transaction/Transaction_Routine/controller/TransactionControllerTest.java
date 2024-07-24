package com.transaction.Transaction_Routine.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.transaction.Transaction_Routine.dto.TransactionDto;
import com.transaction.Transaction_Routine.model.Transaction;
import com.transaction.Transaction_Routine.service.TransactionService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



public class TransactionControllerTest {
	
	@Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private MockMvc mockMvc;

    public TransactionControllerTest() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void testCreateTransaction() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountId(1L);
        transactionDto.setAmount(new BigDecimal("123.45"));
        transactionDto.setOperationTypeId(4);

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(new BigDecimal("123.45"));
        transaction.setType(null); // Set proper type here if needed

        when(transactionService.createTransaction(any(Long.class), any(BigDecimal.class), any(Integer.class)))
                .thenReturn(transaction);

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"account_id\":1,\"operation_type_id\":4,\"amount\":123.45}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"amount\":123.45,\"type\":null}"));
    }

}
