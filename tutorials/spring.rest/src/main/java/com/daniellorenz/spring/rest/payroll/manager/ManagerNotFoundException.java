package com.daniellorenz.spring.rest.payroll.manager;

public class ManagerNotFoundException extends RuntimeException {
    public ManagerNotFoundException(Long id) {
        super("Could not find Manager with id: " + id);
    }
}
