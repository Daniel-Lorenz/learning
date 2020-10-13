package com.daniellorenz.cashbook.transaction;

import lombok.Getter;

import java.time.Instant;
import java.time.OffsetDateTime;

@Getter
public class Transaction {

    private final String description;
    private final double amount;
    private final Instant date;

    public Transaction(double amount, String description, Instant date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
}
