package com.daniellorenz.cashbook.account;

import com.daniellorenz.cashbook.transaction.Transaction;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class AccountBuilder {

    private AccountBuilder() {
        this.transactions = new ArrayList<>();
    }

    List<Transaction> transactions;

    static AccountBuilder account() {
        return new AccountBuilder();
    }

    AccountBuilder withTransaction(double amount) {
        Transaction transaction = Mockito.mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(amount);
        transactions.add(transaction);
        return this;
    }

    AccountBuilder withTransaction(Instant date) {
        Transaction transaction = Mockito.mock(Transaction.class);
        when(transaction.getDate()).thenReturn(date);
        transactions.add(transaction);
        return this;
    }

    AccountBuilder withTransaction(double amount, Instant date) {
        Transaction transaction = Mockito.mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(amount);
        when(transaction.getDate()).thenReturn(date);
        transactions.add(transaction);
        return this;
    }

    AccountBuilder withTransaction(double amount, Instant date, String description) {
        Transaction transaction = Mockito.mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(amount);
        when(transaction.getDate()).thenReturn(date);
        when(transaction.getDescription()).thenReturn(description);
        transactions.add(transaction);
        return this;
    }

    Account build() {
        Account result = new Account();
        transactions.forEach(result::addTransaction);
        return result;
    }
}
