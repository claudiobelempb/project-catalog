package br.com.surb.catalog.modules.color.resource;

import br.com.surb.catalog.modules.color.response.ColorCustomResponse;
import br.com.surb.catalog.modules.color.service.ColorFindByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/colors")
public class ColorFindByIdResource {
    private final ColorFindByIdService colorFindByIdService;
    private final Executor executor;

    public ColorFindByIdResource(ColorFindByIdService colorFindByIdService, Executor executor) {
        this.colorFindByIdService = colorFindByIdService;
        this.executor = executor;
    }

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<ColorCustomResponse>> handle(@PathVariable Long id) {
        return supplyAsync(() -> colorFindByIdService.execute(id), executor).thenApply((r) -> ResponseEntity.ok().body(r));
    }
}
