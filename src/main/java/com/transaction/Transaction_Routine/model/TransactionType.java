package com.transaction.Transaction_Routine.model;

public enum TransactionType {
	PURCHASE(1),
    WITHDRAWAL(2),
    CREDIT_VOUCHER(3),
    PURCHASE_INSTALLMENTS(4);

    private final int id;

    TransactionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TransactionType fromId(int id) {
        for (TransactionType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Transaction Type ID");
    }

}
