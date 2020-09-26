package com.input;

import com.validation.ValidationFacade;
import org.springframework.stereotype.Component;

@Component
public class Reader {

    ValidationFacade validator;

    public Reader(ValidationFacade validator) {
        this.validator = validator;
    }

    public void read(String text){
        validator.validateText(text);
    }
}
