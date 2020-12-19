package com.daniellorenz.spring.rest.payroll.order;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static com.daniellorenz.spring.rest.payroll.order.Status.IN_PROGRESS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public EntityModel<Order> toModel(Order order) {
        EntityModel<Order> model = EntityModel.of(order,
                linkTo(methodOn(OrderController.class).findOne(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).findAll()).withRel("orders")
        );

        model.addIf(order.getStatus() == IN_PROGRESS,
                () -> linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
        model.addIf(order.getStatus() == IN_PROGRESS,
                () -> linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        return model;
    }
}
