package br.com.surb.catalog.modules.role.resource;

import br.com.surb.catalog.modules.role.service.RoleActiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/roles")
public class RoleActiveResource {
    private final RoleActiveService roleActiveService;
    private final Executor executor;

    public RoleActiveResource(RoleActiveService roleActiveService, Executor executor) {
        this.roleActiveService = roleActiveService;
        this.executor = executor;
    }

    @PatchMapping(value = "/active/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> roleActiveService.execute(id), executor)
                .thenApply((r) -> ResponseEntity.noContent().build());
    }
}
