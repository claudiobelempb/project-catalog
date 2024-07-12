package br.com.surb.catalog.modules.category.resource;

import br.com.surb.catalog.modules.category.response.CategoryProductResponse;
import br.com.surb.catalog.modules.category.service.CategoryFindByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/categories")
public class CategoryFindByIdResource {
    private final CategoryFindByIdService categoryFindByIdService;
    private final Executor executor;

    public CategoryFindByIdResource(CategoryFindByIdService categoryFindByIdService, Executor executor) {
        this.categoryFindByIdService = categoryFindByIdService;
        this.executor = executor;
    }

    /*@GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponse> handle(@PathVariable UUID id) {
        CategoryResponse response = categoryFindByIdService.execute(id);
        return ResponseEntity.ok().body(response);
    }*/

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<CategoryProductResponse>> handle(@PathVariable Long id) {
        return supplyAsync(() -> categoryFindByIdService.execute(id), executor).thenApply((response) -> ResponseEntity.ok().body(response));
    }
}
