package com.daniellorenz.cashbook.statement;

import com.daniellorenz.cashbook.transaction.Transaction;
import com.daniellorenz.cashbook.transaction.TransactionBuilder;
import com.daniellorenz.cashbook.transaction.TransactionMockBuilder;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static com.daniellorenz.cashbook.statement.StatementBuilder.statement;
import static com.daniellorenz.cashbook.transaction.TransactionMockBuilder.*;
import static java.time.Instant.*;
import static org.junit.jupiter.api.Assertions.*;

class StatementShould {

    @Test
    void returnCarryOver(){
        Statement statement = statement().withCarryover(100).build();
        var result = statement.getCarryover();

        assertEquals(100.0, result);
    }

    @Test
    void returnZeroCarryOverIfNotGiven(){
        Statement statement = statement().build();
        var result = statement.getCarryover();

        assertEquals(0, result);
    }

    @Test
    void returnCorrectNumberOfTransactions(){
        List<Transaction> transactions = List.of(transactionWith(100, parse("2020-12-03T10:15:30.00Z"), "shoes"));
        Statement statement = statement().withTransactions(transactions).build();

        assertEquals(1, statement.getStatementItems().size());
    }

    @Test
    void returnDateOfTransactions(){
        Instant expectedDate = parse("2020-12-03T10:15:30.00Z");
        List<Transaction> transactions = List.of(transactionWith(100, expectedDate, "shoes"));
        Statement statement = statement().withTransactions(transactions).build();

        assertEquals(1L,
                statement.getStatementItems().stream()
                .map(StatementItem::getDate)
                .filter(expectedDate::equals)
                .count());

    }
    @Test
    void returnAmountOfTransactions(){
        Instant date = parse("2020-12-03T10:15:30.00Z");
        List<Transaction> transactions = List.of(transactionWith(100, date, "shoes"));
        Statement statement = statement().withTransactions(transactions).build();

        assertEquals(1L,
                statement.getStatementItems().stream()
                        .map(StatementItem::getAmount)
                        .filter(Double.valueOf(100)::equals)
                        .count());

    }
    @Test
    void returnDescriptionOfTransactions(){
        Instant date = parse("2020-12-03T10:15:30.00Z");
        List<Transaction> transactions = List.of(transactionWith(100, date, "shoes"));
        Statement statement = statement().withTransactions(transactions).build();

        assertEquals(1L,
                statement.getStatementItems().stream()
                        .map(StatementItem::getDescription)
                        .filter("shoes"::contentEquals)
                        .count());

    }
    @Test
    void returnRunningBalanceOfTransactions(){

        Instant olderDate = parse("2020-12-03T10:15:30.00Z");
        Instant newerDate = parse("2020-12-10T10:15:30.00Z");
        List<Transaction> transactions = List.of(
                transactionWith(-100, newerDate, "shoes"),
                transactionWith(-250, olderDate, "dog"));
        Statement statement = statement().withCarryover(500).withTransactions(transactions).build();

        StatementItem firstItem = statement.getStatementItems().get(0);
        StatementItem secondItem = statement.getStatementItems().get(1);

        assertAll(
                () -> assertEquals(500-250, firstItem.getBalance()),
                () -> assertEquals(500-250-100, secondItem.getBalance())
        );

    }

}