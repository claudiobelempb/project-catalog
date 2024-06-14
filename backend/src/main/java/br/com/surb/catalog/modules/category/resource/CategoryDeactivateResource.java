package br.com.surb.catalog.modules.category.resource;

import br.com.surb.catalog.modules.category.service.CategoryDeactivateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/categories")
public class CategoryDeactivateResource {
    private final CategoryDeactivateService categoryDeactivateService;
    private final Executor executor;

    public CategoryDeactivateResource(CategoryDeactivateService categoryDeactivateService, Executor executor) {
        this.categoryDeactivateService = categoryDeactivateService;
        this.executor = executor;
    }

    @PatchMapping(value = "/deactivate/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> categoryDeactivateService.execute(id), executor)
                .thenApply((result) -> ResponseEntity.noContent().build());
    }
}
