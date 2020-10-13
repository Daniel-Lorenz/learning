package com.daniellorenz.cashbook.account;

import com.daniellorenz.cashbook.transaction.Transaction;
import com.daniellorenz.cashbook.transaction.TransactionBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountShould {


    Account account = new Account();

    @Test
    void returnListOfTransactions(){
        Transaction transaction = Mockito.mock(Transaction.class);
        account.addTransaction(transaction);

        assertEquals(transaction, account.getTransactions().first());

    }
}
