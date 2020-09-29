package com.daniellorenz.blog.cds;

public interface TerminalAction<intype> {

    void execute(intype data);

    Class<intype> getIntype();
}
