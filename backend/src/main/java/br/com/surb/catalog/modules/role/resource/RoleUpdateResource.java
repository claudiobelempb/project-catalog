package br.com.surb.catalog.modules.role.resource;

import br.com.surb.catalog.modules.role.request.RoleUserRequest;
import br.com.surb.catalog.modules.role.response.RoleResponse;
import br.com.surb.catalog.modules.role.service.RoleUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/roles")
public class RoleUpdateResource {
    private final RoleUpdateService roleUpdateService;
    private final Executor executor;

    public RoleUpdateResource(RoleUpdateService roleUpdateService, Executor executor) {
        this.roleUpdateService = roleUpdateService;
        this.executor = executor;
    }

    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<RoleResponse>> handle(@PathVariable Long id, @RequestBody RoleUserRequest request) {
        return supplyAsync(() -> roleUpdateService.execute(id, request), executor).thenApply((category) -> ResponseEntity.ok().body(category));
    }
}
