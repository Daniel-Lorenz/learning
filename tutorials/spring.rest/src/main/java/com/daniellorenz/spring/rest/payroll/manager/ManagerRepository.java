package com.daniellorenz.spring.rest.payroll.manager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Manager findByEmployeesId(Long id);
}
