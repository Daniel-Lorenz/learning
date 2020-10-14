package com.daniellorenz.cashbook.account;

import com.daniellorenz.cashbook.transaction.Transaction;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Instant.parse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountShould {

    Account account = new Account();

    @Test
    void returnListOfTransactions() {
        account = AccountBuilder.account().withTransaction(100).build();

        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void returnTransactionsOrderedFromOldToNew() {
        Instant oldTime = parse("2020-12-03T10:15:30.00Z");
        Instant newTime = parse("2022-12-03T10:15:30.00Z");
        Instant newestTime = parse("2024-12-03T10:15:30.00Z");
        account = AccountBuilder.account()
                                .withTransaction(newTime)
                                .withTransaction(oldTime)
                                .withTransaction(newestTime)
                                .build();

        var result =  account.getTransactions().stream()
                                        .map(Transaction::getDate)
                                        .collect(Collectors.toList());

        assertEquals(List.of(oldTime, newTime, newestTime), result);
    }

    @Test
    void calculateBalance() {

        account = AccountBuilder.account()
                                .withTransaction(100.0)
                                .withTransaction(-8.5)
                                .withTransaction(-2.5)
                                .build();

        var result = account.balance();

        assertEquals(89, result);
    }

    @Test
    void returnStatement(){

        AccountBuilder.account()
                .withTransaction(125, parse("2020-12-03T10:15:30.00Z"), "carryover")
                .withTransaction(100, parse("2020-12-04T10:15:30.00Z"), "deposit")
                .withTransaction(-25, parse("2020-12-10T10:15:30.00Z"), "shoes")
                .withTransaction(-50, parse("2020-12-12T10:15:30.00Z"), "horse")
                .build();

        var result = account.statement();

        assertAll(
                () -> assertEquals(125, result.getCarryover()),
                () -> assertEquals(3, result.getStatementItems().size())
        );
    }



}
