package com.daniellorenz.blog.cds;

import bookshop.Books;

public class RetrieveCarsAction implements InitialAction<Books> {

    Books result;


    @Override
    public void execute() {
        result = Books.create();
    }

    @Override
    public Books getResult() {
        return result;
    }

    @Override
    public Class<Books> getOuttype() {
        return Books.class;
    }
}
