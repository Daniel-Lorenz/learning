package com.it;

import com.validation.ValidationFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidatorFullTest {

    @Autowired
    ValidationFacade validationFacade;


    @Test
    void notNull() {
        Assertions.assertNotNull(validationFacade);
        validationFacade.validateText("text");
    }
}
