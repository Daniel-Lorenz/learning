package com.validation;


import com.sap.cds.services.persistence.PersistenceService;
import org.springframework.stereotype.Component;

@Component
class SimpleValidator implements Validator {

    PersistenceService db;

    SimpleValidator(PersistenceService db) {
        this.db = db;
    }

    @Override
    public void validate() {
        assert db != null;
        System.out.println("validated");
    }
}
