package com.parser;

import java.util.Iterator;

public class ParserBuilder {

    final Steps steps = new Steps();


    public ParserBuilder with(Step step){
        steps.last();
        return this;
    }

    public void build(){

    }
}
