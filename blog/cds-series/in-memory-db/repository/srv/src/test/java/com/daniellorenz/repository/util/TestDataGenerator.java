package com.daniellorenz.repository.util;

import cds.gen.my.bookshop.Books;

public class TestDataGenerator {

    static public Books newBook(String title, Integer stock, Integer ID) {
        Books result = Books.create();
        result.setTitle(title);
        result.setStock(stock);
        result.setId(ID);
        return result;
    }

    static public Books newBookWithoutId(String title, Integer stock) {
        Books result = Books.create();
        result.setTitle(title);
        result.setStock(stock);
        return result;
    }


    
}