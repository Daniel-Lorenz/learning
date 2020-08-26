package com.daniellorenz.repository.books;

import com.daniellorenz.repository.repo.InMemoryDb;
import com.daniellorenz.repository.repo.Repository;
import com.daniellorenz.repository.util.TestDataGenerator;

import org.junit.jupiter.api.*;

import cds.gen.my.bookshop.Books;

public class BooksTest {

    Repository db = new InMemoryDb();
    BookService bookService = new BookService(db);

    @Test
    @DisplayName("Book titles are all caps")
    public void bookTitlesAreAllCaps(){
        Books book = TestDataGenerator.newBook("lower case title", 2, 0);
        bookService.addToCatalogue(book);
        Books found = bookService.findInCatalogue(book.getId());
        Assertions.assertEquals("LOWER CASE TITLE", found.getTitle());
    }
    
}