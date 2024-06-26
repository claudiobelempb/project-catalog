package br.com.surb.catalog.modules.product.resource;

import br.com.surb.catalog.modules.product.service.ProductActiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/products")
public class ProductActiveResource {
    private final ProductActiveService productActiveService;
    private final Executor executor;

    public ProductActiveResource(ProductActiveService productActiveService, Executor executor) {
        this.productActiveService = productActiveService;
        this.executor = executor;
    }

    @PatchMapping(value = "/active/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> productActiveService.execute(id), executor)
                .thenApply((r) -> ResponseEntity.noContent().build());
    }
}
