package com.parser;

public interface TypedStep<I, O> extends Step {

    @Override
    default boolean isTypedStep() {
        return true;
    }

    @Override
    default boolean isUntypedStep() {
        return false;
    }
}
