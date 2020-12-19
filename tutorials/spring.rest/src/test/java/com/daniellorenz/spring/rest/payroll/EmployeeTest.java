package com.daniellorenz.spring.rest.payroll;

import com.daniellorenz.spring.rest.payroll.employee.Employee;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void should (){
        Employee daniel = new Employee();
        daniel.setId(1L);
        daniel.setLastName("daniel");
        daniel.setRole("sleepy joe");

        System.out.println(daniel.toString());

    }

}