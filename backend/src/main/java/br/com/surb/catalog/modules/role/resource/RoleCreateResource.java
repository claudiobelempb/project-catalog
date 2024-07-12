package br.com.surb.catalog.modules.role.resource;

import br.com.surb.catalog.modules.role.request.RoleRequest;
import br.com.surb.catalog.modules.role.response.RoleResponse;
import br.com.surb.catalog.modules.role.service.RoleCreateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/roles")
public class RoleCreateResource {
    private final RoleCreateService roleCreateService;
    private final Executor executor;

    public RoleCreateResource(RoleCreateService roleCreateService, Executor executor) {
        this.roleCreateService = roleCreateService;
        this.executor = executor;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<RoleResponse>> handle(@RequestBody RoleRequest request) {
        RoleResponse response = roleCreateService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return supplyAsync(() -> response, executor).thenApply((__) -> ResponseEntity.created(uri).body(response));
    }
}
