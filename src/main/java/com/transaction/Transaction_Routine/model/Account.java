package com.transaction.Transaction_Routine.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String documentNumber;
	    private BigDecimal balance = BigDecimal.ZERO;

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getDocumentNumber() {
	        return documentNumber;
	    }

	    public void setDocumentNumber(String documentNumber) {
	        this.documentNumber = documentNumber;
	    }

	    public BigDecimal getBalance() {
	        return balance;
	    }

	    public void setBalance(BigDecimal balance) {
	        this.balance = balance;
	    }

}
