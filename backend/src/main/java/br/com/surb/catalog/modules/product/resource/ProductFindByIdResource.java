package br.com.surb.catalog.modules.product.resource;

import br.com.surb.catalog.modules.product.response.ProductResponse;
import br.com.surb.catalog.modules.product.service.ProductFindByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/products")
public class ProductFindByIdResource {
    private final ProductFindByIdService productFindByIdService;
    private final Executor executor;

    public ProductFindByIdResource(ProductFindByIdService productFindByIdService, Executor executor) {
        this.productFindByIdService = productFindByIdService;
        this.executor = executor;
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ProductResponse>> handle(@PathVariable Long id) {
        return supplyAsync(() -> productFindByIdService.execute(id), executor).thenApply((response) -> ResponseEntity.ok().body(response));
    }
}
