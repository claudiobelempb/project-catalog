package br.com.surb.catalog.modules.user.resource;

import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import br.com.surb.catalog.modules.user.service.UserFindByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/users")
public class UserFindByIdResource {
    private final UserFindByIdService userFindByIdService;
    private final Executor executor;

    public UserFindByIdResource(UserFindByIdService userFindByIdService, Executor executor) {
        this.userFindByIdService = userFindByIdService;
        this.executor = executor;
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<UserRoleResponse>> handle(@PathVariable Long id) {
        return supplyAsync(() -> userFindByIdService.execute(id), executor).thenApply((response) -> ResponseEntity.ok().body(response));
    }
}
