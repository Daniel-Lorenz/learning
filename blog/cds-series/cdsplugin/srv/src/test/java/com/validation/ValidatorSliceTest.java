package com.validation;

import com.sap.cds.framework.spring.config.runtime.SpringPersistenceServiceSqlConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@SpringBootTest
@ContextConfiguration(classes = ValidationConfig.class)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:sqlite:file::memory:?cache=shared",
        "spring.datasource.driver-class-name=org.sqlite.JDBC",
        "spring.datasource.initialization-mode=always",
        "spring.datasource.hikari.maximum-pool-size=1"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class ValidatorSliceTest {

    @Autowired
    ValidationFacade validationFacade;

    SpringPersistenceServiceSqlConfiguration d;


    @Test
    void notNull() {
        Assertions.assertNotNull(validationFacade);
        validationFacade.validateText("text");
    }
}
