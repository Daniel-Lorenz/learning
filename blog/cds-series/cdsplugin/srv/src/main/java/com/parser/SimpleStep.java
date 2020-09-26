package com.parser;

public abstract class SimpleStep<I,O> implements TypedStep<I, O> {

    private final Class<O> outtype;
    private final Class<I> intype;

    public SimpleStep(Class<I> intype, Class<O> outtype){
        this.intype = intype;
        this.outtype = outtype;
    }

    public abstract O execute(I input);


}
