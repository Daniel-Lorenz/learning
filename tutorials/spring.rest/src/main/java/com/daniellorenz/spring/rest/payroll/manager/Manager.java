package com.daniellorenz.spring.rest.payroll.manager;

import com.daniellorenz.spring.rest.payroll.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Manager {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private List<Employee> employees = new ArrayList<>();

    public Manager(String name){
        this.name = name;
    }

}
