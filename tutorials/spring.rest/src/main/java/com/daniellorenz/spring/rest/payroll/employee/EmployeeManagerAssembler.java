package com.daniellorenz.spring.rest.payroll.employee;

import com.daniellorenz.spring.rest.payroll.manager.ManagerController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeManagerAssembler implements RepresentationModelAssembler<EmployeeWithManager, EntityModel<EmployeeWithManager>> {
    @Override
    public EntityModel<EmployeeWithManager> toModel(EmployeeWithManager entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withRel("detailedEmployees)"),
                linkTo(methodOn(EmployeeController.class).findOneDetailedEmployee(entity.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).findOne(entity.getId())).withRel("summary")
        );
    }

    @Override
    public CollectionModel<EntityModel<EmployeeWithManager>> toCollectionModel(Iterable<? extends EmployeeWithManager> entities) {
        List<EntityModel<EmployeeWithManager>> entityModels = StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"),
                linkTo(methodOn(ManagerController.class).findAll()).withRel("managers"));
    }
}
