package com.daniellorenz.cashbook.statement;

import com.daniellorenz.cashbook.transaction.Transaction;
import com.daniellorenz.cashbook.transaction.TransactionComparator;

import java.util.ArrayList;
import java.util.List;

public class StatementBuilder {
    private double carryover = 0.0;
    private final List<Transaction> transactions;

    public StatementBuilder() {
        this.transactions = new ArrayList<>();
    }

    public static StatementBuilder statement() {
        return new StatementBuilder();
    }

    public StatementBuilder withCarryover(double carryover) {
        this.carryover = carryover;
        return this;
    }

    public Statement build() {

        List<StatementItem> items = new ArrayList<>();
        transactions.sort(new TransactionComparator());

        double runningBalance = carryover;
        for(var transaction : transactions){
            runningBalance += transaction.getAmount();
            StatementItem item = new StatementItem(transaction.getDate(),
                    transaction.getAmount(),
                    transaction.getDescription(),
                    runningBalance);
            items.add(item);
        }

        return new Statement(carryover, items);
    }

    public StatementBuilder withTransactions(List<Transaction> transactions) {
        this.transactions.addAll(transactions);
        return this;
    }
}
