package com.daniellorenz.cashbook.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class TransactionShould {

    private Transaction transaction;
    private TransactionBuilder builder;
    private Clock clock;

    @BeforeEach
    void setUp() {
        Instant fixedInstant = Instant.parse("2020-12-03T10:15:30.00Z");
        clock = Clock.fixed(fixedInstant, ZoneId.of("UTC+00:00"));
        builder = new TransactionBuilder(clock);
        transaction = builder.transactionWith(100.00, "Socks");
    }

    @Test
    void returnAmountWhenAsked(){
        assertEquals(100, transaction.getAmount());
    }

    @Test
    void returnDescriptionWhenAsked(){
        assertEquals("Socks", transaction.getDescription());
    }

    @Test
    void returnDateWhenAsked(){
        assertEquals(clock.instant(), transaction.getDate());
    }
}
