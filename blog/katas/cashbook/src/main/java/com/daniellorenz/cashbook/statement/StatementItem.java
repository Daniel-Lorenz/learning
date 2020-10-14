package com.daniellorenz.cashbook.statement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class StatementItem {
    private final Instant date;
    private final double amount;
    private final String description;
    private final double balance;
}
