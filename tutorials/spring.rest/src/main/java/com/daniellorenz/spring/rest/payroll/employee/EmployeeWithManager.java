package com.daniellorenz.spring.rest.payroll.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

@Value
@JsonPropertyOrder({"id", "name", "role", "manager"})
public class EmployeeWithManager {

    @JsonIgnore
    Employee employee;

    public Long getId() {
        return this.employee.getId();
    }

    public String getName() {
        return this.employee.getName().orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));
    }

    public String getRole() {
        return this.employee.getRole().orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));
    }

    public String getManager() {
        return this.employee.getManager().getName();
    }
}
