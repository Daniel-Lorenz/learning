package com.daniellorenz.cashbook.transaction;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

@Component
public class TransactionBuilder {

    private final Clock clock;

    public TransactionBuilder(Clock clock) {
        this.clock = clock;
    }

    Transaction transactionWith(Double amount, String description) {
        Instant date = clock.instant();
        return new Transaction(amount, description, date);
    }

}
