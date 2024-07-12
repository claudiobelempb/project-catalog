package br.com.surb.catalog.modules.product.resource;

import br.com.surb.catalog.modules.product.response.ProductCategoryResponse;
import br.com.surb.catalog.modules.product.response.ProductResponse;
import br.com.surb.catalog.modules.product.service.ProductFindAllService;
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
@RequestMapping(value = "/products")
public class ProductFindAllResource {
    private final ProductFindAllService productFindAllService;
    private final Executor executor;

    public ProductFindAllResource(ProductFindAllService productFindAllService, Executor executor) {
        this.productFindAllService = productFindAllService;
        this.executor = executor;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Page<ProductCategoryResponse>>> handle(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return CompletableFuture.supplyAsync(() -> productFindAllService.execute(pageRequest), executor)
                .thenApply((r) -> ResponseEntity.ok().body(r));
    }
}
