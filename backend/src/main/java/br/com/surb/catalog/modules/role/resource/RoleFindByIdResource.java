package br.com.surb.catalog.modules.role.resource;

import br.com.surb.catalog.modules.role.response.RoleUserResponse;
import br.com.surb.catalog.modules.role.service.RoleFindByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/roles")
public class RoleFindByIdResource {
    private final RoleFindByIdService roleFindByIdService;
    private final Executor executor;

    public RoleFindByIdResource(RoleFindByIdService roleFindByIdService, Executor executor) {
        this.roleFindByIdService = roleFindByIdService;
        this.executor = executor;
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<RoleUserResponse>> handle(@PathVariable Long id) {
        return supplyAsync(() -> roleFindByIdService.execute(id), executor).thenApply((r) -> ResponseEntity.ok().body(r));
    }
}
