package com.transaction.Transaction_Routine.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.transaction.Transaction_Routine.model.Account;
import com.transaction.Transaction_Routine.service.AccountService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




public class AccountControllerTest {
	
	@Mock
    private AccountService accountService;
	
	 @InjectMocks
	 private AccountController accountController;
	 
	 private MockMvc mockMvc;
	 
	 public AccountControllerTest() {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	    }
	 
	 @Test
	    public void testCreateAccount() throws Exception {
	        Account account = new Account();
	        account.setId(1L);
	        account.setDocumentNumber("12345678900");

	        when(accountService.createAccount(any(String.class))).thenReturn(account);

	        mockMvc.perform(post("/accounts")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"document_number\": \"12345678900\"}"))
	                .andExpect(status().isCreated())
	                .andExpect(content().json("{\"id\":1,\"documentNumber\":\"12345678900\"}"));
	    }
	 
	 @Test
	    public void testGetAccount() throws Exception {
	        Account account = new Account();
	        account.setId(1L);
	        account.setDocumentNumber("12345678900");

	        when(accountService.getAccount(1L)).thenReturn(account);

	        mockMvc.perform(get("/accounts/1"))
	                .andExpect(status().isOk())
	                .andExpect(content().json("{\"id\":1,\"documentNumber\":\"12345678900\"}"));
	    }

}
