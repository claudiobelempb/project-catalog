package br.com.surb.catalog.modules.role.resource;

import br.com.surb.catalog.modules.role.service.RoleDeactivateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/roles")
public class RoleDeactivateResource {
    private final RoleDeactivateService roleDeactivateService;
    private final Executor executor;

    public RoleDeactivateResource(RoleDeactivateService roleDeactivateService, Executor executor) {
        this.roleDeactivateService = roleDeactivateService;
        this.executor = executor;
    }

    @PatchMapping(value = "/deactivate/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> roleDeactivateService.execute(id), executor)
                .thenApply((result) -> ResponseEntity.noContent().build());
    }
}
