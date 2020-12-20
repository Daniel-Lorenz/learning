package com.daniellorenz.spring.rest.payroll.employee;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler assembler;
    private final EmployeeManagerAssembler employeeManagerAssembler;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler assembler, EmployeeManagerAssembler employeeManagerAssembler) {
        this.employeeRepository = employeeRepository;
        this.assembler = assembler;
        this.employeeManagerAssembler = employeeManagerAssembler;
    }

    @GetMapping(path = "/employees")
    CollectionModel<EntityModel<Employee>> findAll() {
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel()
                        .andAffordance(afford(methodOn(EmployeeController.class).createEmployee(null))));
    }

    @PostMapping(path = "/employees")
    ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        EntityModel<Employee> employeeEntityModel = assembler.toModel(employeeRepository.save(employee));
        return ResponseEntity.created(
                employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employeeEntityModel);
    }

    @GetMapping(path = "/employees/{id}")
    EntityModel<Employee> findOne(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return assembler.toModel(employee);
    }

    @PutMapping(path = "/employees/{id}")
    ResponseEntity<?> upsertOne(@RequestBody Employee newEmployee, @PathVariable Long id) {
        Employee updatedEmployee = employeeRepository.findById(id)
                .map(employee -> {
                    newEmployee.getLastName().ifPresent(employee::setLastName);
                    newEmployee.getRole().ifPresent(employee::setRole);
                    return employeeRepository.save(employee);
                }).orElseGet(() -> {
                            newEmployee.setId(id);
                            return employeeRepository.save(newEmployee);
                        }
                );

        EntityModel<Employee> employeeEntityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity.created(
                employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employeeEntityModel);
    }

    @DeleteMapping(path = "/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/managers/{id}/employees")
    public ResponseEntity<?> findAllByManagerId(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toCollectionModel(employeeRepository.findByManagerId(id)));
    }

    @GetMapping("/employees/detailed")
    ResponseEntity<?> findAllDetailedEmployees() {
        return ResponseEntity.ok(
                employeeManagerAssembler.toCollectionModel(
                        employeeRepository.findAll().stream()
                                .map(EmployeeWithManager::new)
                                .collect(Collectors.toList())));
    }

    @GetMapping("/employees/{id}/detailed")
    ResponseEntity<?> findOneDetailedEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(
                employeeManagerAssembler.toModel(
                        employeeRepository.findById(id)
                                .map(EmployeeWithManager::new)
                                .orElseThrow(() -> new EmployeeNotFoundException(id))
                ));

    }
}
