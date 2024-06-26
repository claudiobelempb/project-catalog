package br.com.surb.catalog.modules.product.resource;

import br.com.surb.catalog.modules.product.service.ProductDeactivateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/products")
public class ProductDeactivateResource {
    private final ProductDeactivateService productDeactivateService;
    private final Executor executor;

    public ProductDeactivateResource(ProductDeactivateService productDeactivateService, Executor executor) {
        this.productDeactivateService = productDeactivateService;
        this.executor = executor;
    }

    @PatchMapping(value = "/deactivate/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> productDeactivateService.execute(id), executor)
                .thenApply((r) -> ResponseEntity.noContent().build());
    }
}
