package com.daniellorenz.spring.rest.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void should (){
        Employee daniel = new Employee();
        daniel.setId(1L);
        daniel.setName("daniel");
        daniel.setRole("sleepy joe");

        System.out.println(daniel.toString());

    }

}