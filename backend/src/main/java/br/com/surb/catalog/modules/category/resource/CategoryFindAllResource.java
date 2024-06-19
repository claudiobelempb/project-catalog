package br.com.surb.catalog.modules.category.resource;

import br.com.surb.catalog.modules.category.response.CategoryResponse;
import br.com.surb.catalog.modules.category.service.CategoryFindAllService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/categories")
public class CategoryFindAllResource {
    private final CategoryFindAllService categoryFindAllService;
    private final Executor executor;

    public CategoryFindAllResource(CategoryFindAllService categoryFindAllService, Executor executor) {
        this.categoryFindAllService = categoryFindAllService;
        this.executor = executor;
    }

    /*@GetMapping
    public CompletableFuture<ResponseEntity<Page<CategoryResponse>>> handle(
            Pageable pageable
    ) {
        return CompletableFuture.supplyAsync(() -> categoryFindAllService.execute(pageable), executor)
                .thenApply((category) -> ResponseEntity.ok().body(category));
    }*/

    @GetMapping
    public CompletableFuture<ResponseEntity<Page<CategoryResponse>>> handle(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return CompletableFuture.supplyAsync(() -> categoryFindAllService.execute(pageRequest), executor)
                .thenApply((category) -> ResponseEntity.ok().body(category));
    }
}
