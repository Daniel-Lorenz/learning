package com.daniellorenz.spring.rest.payroll.employee;


import com.daniellorenz.spring.rest.payroll.manager.Manager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;
import java.util.Optional;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Setter
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String lastName;
    private String firstName;
    private String role;

    @JsonIgnore
    @OneToOne
    private Manager manager;

    public Employee(String firstName, String lastName, String role, Manager manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public Manager getManager() {
        return manager;
    }

    public Optional<String> getName() {
        if (firstName == null && lastName == null) {
            return Optional.empty();
        }

        return Optional.of(firstName + " " + lastName);
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        firstName = parts[0];
        lastName = parts[1];
    }

    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }

    public Optional<String> getRole() {
        return Optional.of(role);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
                + '\'' + ", role='" + this.role + '\'' + '}';
    }
}
