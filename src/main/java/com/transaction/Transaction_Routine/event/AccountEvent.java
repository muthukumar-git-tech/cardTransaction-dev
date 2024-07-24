package com.transaction.Transaction_Routine.event;

public class AccountEvent {
	 public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	private Long accountId;
	 private String documentNumber;

}
