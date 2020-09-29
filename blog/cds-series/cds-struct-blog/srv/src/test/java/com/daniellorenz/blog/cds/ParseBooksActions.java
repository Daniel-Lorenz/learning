package com.daniellorenz.blog.cds;

import bookshop.Books;
import carshop.Cars;

public class ParseBooksActions implements IntermediateAction<bookshop.Books, carshop.Cars> {

    Cars result;

    @Override
    public Class<Books> getIntype() {
        return Books.class;
    }

    @Override
    public Class<Cars> getOuttype() {
        return Cars.class;
    }

    @Override
    public Cars getResult() {
        return result;
    }

    @Override
    public void execute(Books data) {
        result = Cars.create();
    }
}
