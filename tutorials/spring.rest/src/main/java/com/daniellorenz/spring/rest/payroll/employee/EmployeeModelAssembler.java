package com.daniellorenz.spring.rest.payroll.employee;

import com.daniellorenz.spring.rest.payroll.manager.ManagerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel()
                        .andAffordance(afford(methodOn(EmployeeController.class).upsertOne(null, employee.getId())))
                        .andAffordance(afford(methodOn(EmployeeController.class).deleteEmployee(employee.getId()))),
                linkTo(methodOn(ManagerController.class).findOneByEmployeeId(employee.getId())).withRel("managers"),
                linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees")
                        .andAffordance(afford(methodOn(EmployeeController.class).createEmployee(null))));
    }

}
