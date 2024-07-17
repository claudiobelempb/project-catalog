package br.com.surb.catalog.modules.color.resource;

import br.com.surb.catalog.modules.color.service.ColorActiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/colors")
public class ColorActiveResource {
    private final ColorActiveService colorActiveService;
    private final Executor executor;

    public ColorActiveResource(ColorActiveService colorActiveService, Executor executor) {
        this.colorActiveService = colorActiveService;
        this.executor = executor;
    }


    @PatchMapping(value = "/active/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> colorActiveService.execute(id), executor)
                .thenApply((r) -> ResponseEntity.noContent().build());
    }
}
