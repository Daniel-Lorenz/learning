package com.validation;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@ComponentScan(basePackages = {"com.validation", "com.sap.cds"})
public class ValidationConfig {

    @Bean
    DataSource getDataSource() {
        return DataSourceBuilder.create().driverClassName("org.sqlite.JDBC").url("jdbc:sqlite:file::memory:?cache=shared").type(SQLiteDataSource.class).build();
    }
}
