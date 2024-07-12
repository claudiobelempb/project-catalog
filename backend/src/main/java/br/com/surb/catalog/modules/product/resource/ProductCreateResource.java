package br.com.surb.catalog.modules.product.resource;

import br.com.surb.catalog.modules.product.request.ProductCategoryRequest;
import br.com.surb.catalog.modules.product.response.ProductCategoryResponse;
import br.com.surb.catalog.modules.product.response.ProductResponse;
import br.com.surb.catalog.modules.product.service.ProductCreateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/products")
public class ProductCreateResource {

    private final ProductCreateService productCreateService;

    public ProductCreateResource(ProductCreateService productCreateService) {
        this.productCreateService = productCreateService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<ProductCategoryResponse>> handle(@RequestBody ProductCategoryRequest request) {
        ProductCategoryResponse response = productCreateService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return supplyAsync(() -> response).thenApply((__) -> ResponseEntity.created(uri).body(response));
    }
}
