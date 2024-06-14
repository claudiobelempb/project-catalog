package br.com.surb.catalog.modules.category.resource;

import br.com.surb.catalog.modules.category.service.CategoryActiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/categories")
public class CategoryActiveResource {
    private final CategoryActiveService categoryActiveService;
    private final Executor executor;

    public CategoryActiveResource(CategoryActiveService categoryActiveService, Executor executor) {
        this.categoryActiveService = categoryActiveService;
        this.executor = executor;
    }

    @PatchMapping(value = "/active/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> categoryActiveService.execute(id), executor)
                .thenApply((r) -> ResponseEntity.noContent().build());
    }
}
