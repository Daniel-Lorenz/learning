package com.daniellorenz.repository.integrationtests;

import com.daniellorenz.repository.books.BookService;
import com.daniellorenz.repository.util.TestDataGenerator;
import com.sap.cds.reflect.CdsModel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cds.gen.my.bookshop.Books;

@SpringBootTest
public class BooksIntegrationTest {

    @Autowired
    BookService bookService;

    @Test
    @DisplayName("Book titles are all caps")
    public void bookTitlesAreAllCaps(){
        Books book = TestDataGenerator.newBook("lower case title", 2, 0);
        bookService.addToCatalogue(book);
        Books found = bookService.findInCatalogue(book.getId());
        Assertions.assertEquals("LOWER CASE TITLE", found.getTitle());
    }
    
}