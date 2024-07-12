package br.com.surb.catalog.modules.user.resource;

import br.com.surb.catalog.modules.user.service.UserDeactivateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/users")
public class UserDeactivateResource {
    private final UserDeactivateService userDeactivateService;
    private final Executor executor;

    public UserDeactivateResource(UserDeactivateService userDeactivateService, Executor executor) {
        this.userDeactivateService = userDeactivateService;
        this.executor = executor;
    }

    @PatchMapping(value = "/deactivate/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> userDeactivateService.execute(id), executor)
                .thenApply((r) -> ResponseEntity.noContent().build());
    }
}
