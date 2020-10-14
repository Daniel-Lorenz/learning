package com.daniellorenz.cashbook.transaction;

import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionMockBuilder {

    public static Transaction transactionWith(Instant date) {
        Transaction transaction = mock(Transaction.class);
        when(transaction.getDate()).thenReturn(date);
        return transaction;
    }

    public static Transaction transactionWith(double amount, Instant date) {
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(amount);
        when(transaction.getDate()).thenReturn(date);
        return transaction;
    }

    public static Transaction transactionWith(double amount, Instant date, String description) {
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(amount);
        when(transaction.getDate()).thenReturn(date);
        when(transaction.getDescription()).thenReturn(description);
        return transaction;
    }
}
