package com.daniellorenz.spring.rest.payroll;

import com.daniellorenz.spring.rest.payroll.employee.Employee;
import com.daniellorenz.spring.rest.payroll.employee.EmployeeRepository;
import com.daniellorenz.spring.rest.payroll.order.Order;
import com.daniellorenz.spring.rest.payroll.order.OrderRepository;
import com.daniellorenz.spring.rest.payroll.order.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDemoData {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository, OrderRepository orderRepository) {

        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }
}
