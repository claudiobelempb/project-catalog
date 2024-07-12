package br.com.surb.catalog.modules.user.resource;

import br.com.surb.catalog.modules.user.service.UserActiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/users")
public class UserActiveResource {
    private final UserActiveService userActiveService;
    private final Executor executor;

    public UserActiveResource(UserActiveService userActiveService, Executor executor) {
        this.userActiveService = userActiveService;
        this.executor = executor;
    }

    @PatchMapping(value = "/active/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> userActiveService.execute(id), executor)
                .thenApply((r) -> ResponseEntity.noContent().build());
    }
}
