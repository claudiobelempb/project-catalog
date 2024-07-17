package br.com.surb.catalog.modules.user.resource;

import br.com.surb.catalog.modules.user.request.UserUpdateRequest;
import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import br.com.surb.catalog.modules.user.service.UserUpdateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/users")
public class UserUpdateResource {
    private final UserUpdateService userUpdateService;
    private final Executor executor;

    public UserUpdateResource(UserUpdateService userUpdateService, Executor executor) {
        this.userUpdateService = userUpdateService;
        this.executor = executor;
    }

    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<UserRoleResponse>> handle(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        return supplyAsync(() -> userUpdateService.execute(id, request), executor).thenApply((product) -> ResponseEntity.ok().body(product));
    }

}
