package com.daniellorenz.spring.rest.payroll;

import com.daniellorenz.spring.rest.payroll.employee.Employee;
import com.daniellorenz.spring.rest.payroll.manager.Manager;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void should (){
        Employee daniel = new Employee(1L, "lorenz", "daniel", "sleepy joe",new Manager("Gandalf"));

        System.out.println(daniel.toString());

    }

}