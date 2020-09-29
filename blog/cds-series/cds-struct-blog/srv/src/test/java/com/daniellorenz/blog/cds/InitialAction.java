package com.daniellorenz.blog.cds;

public interface InitialAction<outtype> {

    void execute();

    outtype getResult();

    Class<outtype> getOuttype();
}
