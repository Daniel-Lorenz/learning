package com.parser;

public interface Step {

    default boolean isTypedStep(){
        return false;
    }

    default boolean isUntypedStep(){
        return true;
    }
}
