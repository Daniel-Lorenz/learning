package com.validation;

import org.springframework.stereotype.Component;

@Component
public class ValidationFacade {


    private Validator validator;

    public ValidationFacade(Validator validator) {
        this.validator = validator;
    }

    public void validateText(String text){
        validator.validate();
    }
}
