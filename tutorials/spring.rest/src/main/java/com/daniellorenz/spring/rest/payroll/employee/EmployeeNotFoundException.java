package com.daniellorenz.spring.rest.payroll.employee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find Employee with ID " + id);
    }
}
