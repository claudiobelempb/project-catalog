package br.com.surb.catalog.modules.role.resource;

import br.com.surb.catalog.modules.role.service.RoleDeleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RoleDeleteResource {
    private final RoleDeleteService roleDeleteService;

    public RoleDeleteResource(RoleDeleteService roleDeleteService) {
        this.roleDeleteService = roleDeleteService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> handle(@PathVariable Long id) {
        roleDeleteService.execute(id);
        return ResponseEntity.noContent().build();
    }
}
