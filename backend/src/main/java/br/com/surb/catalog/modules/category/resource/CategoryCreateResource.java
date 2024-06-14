package br.com.surb.catalog.modules.category.resource;

import br.com.surb.catalog.modules.category.request.CategoryRequest;
import br.com.surb.catalog.modules.category.response.CategoryResponse;
import br.com.surb.catalog.modules.category.service.CategoryCreateService;
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
@RequestMapping(value = "/categories")
public class CategoryCreateResource {
    private final CategoryCreateService categoryCreateService;
    private final Executor executor;

    public CategoryCreateResource(CategoryCreateService categoryCreateService, Executor executor) {
        this.categoryCreateService = categoryCreateService;
        this.executor = executor;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<CategoryResponse>> handle(@RequestBody CategoryRequest request) {
        CategoryResponse response = categoryCreateService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return supplyAsync(() -> response, executor).thenApply((__) -> ResponseEntity.created(uri).body(response));
    }
}
