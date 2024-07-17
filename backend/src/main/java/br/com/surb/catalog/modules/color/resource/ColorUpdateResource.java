package br.com.surb.catalog.modules.color.resource;

import br.com.surb.catalog.modules.color.request.ColorUpdateRequest;
import br.com.surb.catalog.modules.color.response.ColorResponse;
import br.com.surb.catalog.modules.color.service.ColorUpdateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/colors")
public class ColorUpdateResource {

    private final ColorUpdateService colorUpdateService;
    private final Executor executor;

    public ColorUpdateResource(ColorUpdateService colorUpdateService, Executor executor) {
        this.colorUpdateService = colorUpdateService;
        this.executor = executor;
    }

    @PutMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ColorResponse>> handle(@PathVariable Long id, @Valid @RequestBody ColorUpdateRequest request) {
        return supplyAsync(() -> colorUpdateService.execute(id, request), executor).thenApply((category) -> ResponseEntity.ok().body(category));
    }
}
