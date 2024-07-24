package com.transaction.Transaction_Routine.dto;

import java.math.BigDecimal;

public class TransactionDto {
	 private Long accountId;
	 public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getOperationTypeId() {
		return operationTypeId;
	}
	public void setOperationTypeId(int operationTypeId) {
		this.operationTypeId = operationTypeId;
	}
	private BigDecimal amount;
	 private int operationTypeId;

}
