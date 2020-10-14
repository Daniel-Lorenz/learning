package com.daniellorenz.cashbook.account;

import com.daniellorenz.cashbook.statement.Statement;
import com.daniellorenz.cashbook.transaction.Transaction;
import com.daniellorenz.cashbook.transaction.TransactionComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {

    List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions.stream().sorted(new TransactionComparator()).collect(Collectors.toList());
    }

    public double balance() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public Statement statement() {
        return null;
    }

}
