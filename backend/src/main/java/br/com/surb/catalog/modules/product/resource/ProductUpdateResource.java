package br.com.surb.catalog.modules.product.resource;

import br.com.surb.catalog.modules.product.request.ProductRequestCustom;
import br.com.surb.catalog.modules.product.response.ProductResponse;
import br.com.surb.catalog.modules.product.service.ProductUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/products")
public class ProductUpdateResource {

    private final ProductUpdateService productUpdateService;

    public ProductUpdateResource(ProductUpdateService productUpdateService) {
        this.productUpdateService = productUpdateService;
    }

    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ProductResponse>> handle(@PathVariable Long id, @RequestBody ProductRequestCustom request) {
        return supplyAsync(() -> productUpdateService.execute(id, request)).thenApply((entity) -> ResponseEntity.ok().body(entity));
    }
}
