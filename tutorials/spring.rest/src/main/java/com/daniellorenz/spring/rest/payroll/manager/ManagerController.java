package com.daniellorenz.spring.rest.payroll.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerRepository repository;
    private final ManagerResourceAssembler assembler;

    @GetMapping("/managers")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(assembler.toCollectionModel(repository.findAll()));
    }

    @PostMapping("/managers")
    ResponseEntity<EntityModel<Manager>> createNew(@RequestBody Manager newManager) {
        EntityModel<Manager> managerEntityModel = assembler.toModel(repository.save(newManager));
        return ResponseEntity.created(
                managerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(managerEntityModel);
    }

    @PutMapping("/managers/{id}")
    ResponseEntity<?> updateManager(@RequestBody Manager newManager, @PathVariable Long id) {
        Manager updatedManager = repository.findById(id)
                .map(manager -> {
                    manager.setName(newManager.getName());
                    return repository.save(manager);
                }).orElseGet(() -> {
                            newManager.setId(id);
                            return repository.save(newManager);
                        }
                );

        EntityModel<Manager> managerEntityModel = assembler.toModel(updatedManager);

        return ResponseEntity.created(
                managerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(managerEntityModel);
    }

    @GetMapping("/managers/{id}")
    EntityModel<Manager> findOne(@PathVariable Long id) {
        return assembler.toModel(repository.findById(id).orElseThrow(() -> new ManagerNotFoundException(id)));
    }

    @DeleteMapping("/managers/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees/{id}/managers")
    public ResponseEntity<?> findOneByEmployeeId(@PathVariable Long id){
        return ResponseEntity.ok(assembler.toModel(repository.findByEmployeesId(id)));
    }
}
