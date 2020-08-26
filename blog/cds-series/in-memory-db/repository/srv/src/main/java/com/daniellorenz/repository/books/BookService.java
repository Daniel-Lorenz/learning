package com.daniellorenz.repository.books;

import com.daniellorenz.repository.repo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cds.gen.my.bookshop.Books;

@Component
public class BookService {
    
    Repository db;

    @Autowired
    public BookService(Repository db) {
        this.db = db;
	}

	public void addToCatalogue(Books book) {
        db.insert(book, Books.class);
	}

	public Books findInCatalogue(Integer id) {
        Books result = db.select(id, Books.class).first().get().as(Books.class);
        capitalizeTitle(result);
        return result;
    }
    
    private void capitalizeTitle(Books book){
        book.setTitle(book.getTitle().toUpperCase());
    }
    
}