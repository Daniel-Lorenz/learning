package com.daniellorenz.spring.rest.payroll.manager;

import com.daniellorenz.spring.rest.payroll.employee.EmployeeController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class ManagerResourceAssembler implements RepresentationModelAssembler<Manager, EntityModel<Manager>> {

    @Override
    public EntityModel<Manager> toModel(Manager manager) {
        return EntityModel.of(manager,
                linkTo(methodOn(ManagerController.class).findOne(manager.getId())).withSelfRel()
                        .andAffordance(afford(methodOn(ManagerController.class).updateManager(null, manager.getId())))
                        .andAffordance(afford(methodOn(ManagerController.class).delete(manager.getId()))),
                linkTo(methodOn(EmployeeController.class).findAllByManagerId(manager.getId())).withRel("employees"),
                linkTo(methodOn(ManagerController.class).findAll()).withRel("managers")
                        .andAffordance(afford(methodOn(ManagerController.class).createNew(null))));
    }

    @Override
    public CollectionModel<EntityModel<Manager>> toCollectionModel(Iterable<? extends Manager> managers) {
        List<EntityModel<Manager>> managersEntityModel = StreamSupport.stream(managers.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(managersEntityModel,
                linkTo(methodOn(ManagerController.class).findAll()).withSelfRel()
                        .andAffordance(afford(methodOn(ManagerController.class).createNew(null))))
                ;
    }
}