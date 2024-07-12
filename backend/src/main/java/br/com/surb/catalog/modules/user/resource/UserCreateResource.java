package br.com.surb.catalog.modules.user.resource;

import br.com.surb.catalog.modules.user.request.UserRoleRequest;
import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import br.com.surb.catalog.modules.user.service.UserCreateService;
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
@RequestMapping(value = "/users")
public class UserCreateResource {
    private final UserCreateService userCreateService;
    private final Executor executor;

    public UserCreateResource(UserCreateService userCreateService, Executor executor) {
        this.userCreateService = userCreateService;
        this.executor = executor;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<UserRoleResponse>> handle(@RequestBody UserRoleRequest request) {
        UserRoleResponse response = userCreateService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return supplyAsync(() -> response, executor).thenApply((__) -> ResponseEntity.created(uri).body(response));
    }
}
