package com.daniellorenz.blog.cds;

public interface IntermediateAction<intype, outtype> {

    Class<intype> getIntype();

    Class<outtype> getOuttype();

    outtype getResult();

    void execute(intype data);

}
