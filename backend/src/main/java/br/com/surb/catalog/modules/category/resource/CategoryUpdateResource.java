package br.com.surb.catalog.modules.category.resource;

import br.com.surb.catalog.modules.category.request.CategoryRequest;
import br.com.surb.catalog.modules.category.request.CategoryUpdateRequest;
import br.com.surb.catalog.modules.category.response.CategoryResponse;
import br.com.surb.catalog.modules.category.service.CategoryUpdateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/categories")
public class CategoryUpdateResource {
    private final CategoryUpdateService categoryUpdateService;
    private final Executor executor;

    public CategoryUpdateResource(CategoryUpdateService categoryUpdateService, Executor executor) {
        this.categoryUpdateService = categoryUpdateService;
        this.executor = executor;
    }

    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<CategoryResponse>> handle(@PathVariable Long id, @Valid @RequestBody CategoryUpdateRequest request) {
        return supplyAsync(() -> categoryUpdateService.execute(id, request), executor).thenApply((response) -> ResponseEntity.ok().body(response));
    }
}
