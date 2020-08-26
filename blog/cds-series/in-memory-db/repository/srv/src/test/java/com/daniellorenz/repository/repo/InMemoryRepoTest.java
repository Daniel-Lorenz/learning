package com.daniellorenz.repository.repo;

import com.daniellorenz.repository.util.TestDataGenerator;
import com.sap.cds.Result;
import com.sap.cds.services.ServiceException;

import org.junit.jupiter.api.*;
import org.opentest4j.MultipleFailuresError;

import cds.gen.my.bookshop.Books;

class InMemoryRepoTest {

    Repository inMemoryDb = new InMemoryDb();

    @Test
    @DisplayName("A Book can be inserted and selected by ID")
    void insertAndSelectBookTest(){
        Books book = TestDataGenerator.newBook("Title", 10, 1);
        inMemoryDb.insert(book, Books.class);
        Books found = inMemoryDb.select(1, Books.class).first().get().as(Books.class);
        assertEquals(book, found);
    }

    @Test
    @DisplayName("Insert generates ID if missing in data")
    void insertGeneratesIdIfMissing(){
        Books book = TestDataGenerator.newBookWithoutId("Title", 10);
        Books found = inMemoryDb.insert(book, Books.class).first().get().as(Books.class);
        Assertions.assertNotNull(found.getId());
    }

    @Test
    @DisplayName("Result object is returned after insert")
    void insertReturnsResult(){
        Books book = TestDataGenerator.newBook("Title", 10, 1);
        Result result = inMemoryDb.insert(book, Books.class);
        Books found = result.first().get().as(Books.class);
        assertEquals(book, found);
    }

    @Test
    @DisplayName("Insert throws CdsServiceException if ID is already in use")
    void primaryKeyConstraintException(){
        Books book = TestDataGenerator.newBook("Title", 10, 1);
        inMemoryDb.insert(book, Books.class);
        Assertions.assertThrows(ServiceException.class, 
            () -> {inMemoryDb.insert(book, Books.class);}
        );
    }

    private void assertEquals(Books expected, Books actual) throws MultipleFailuresError {
        Assertions.assertAll(
            () -> Assertions.assertEquals(expected.getId(), actual.getId()),
            () -> Assertions.assertEquals(expected.getTitle(), actual.getTitle()),
            () -> Assertions.assertEquals(expected.getStock(), actual.getStock())
        );
    }

  
    
    
}