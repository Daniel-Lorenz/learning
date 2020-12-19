package com.daniellorenz.spring.rest.payroll.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find Order with ID " + id);
    }
}
