package br.com.surb.catalog.modules.color.resource;

import br.com.surb.catalog.modules.color.service.ColorDeactivateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/colors")
public class ColorDeactivateResource {
    private final ColorDeactivateService colorDeactivateService;
    private final Executor executor;

    public ColorDeactivateResource(ColorDeactivateService colorDeactivateService, Executor executor) {
        this.colorDeactivateService = colorDeactivateService;
        this.executor = executor;
    }

    @PatchMapping(value = "/deactivate/{id}")
    public CompletableFuture<ResponseEntity<Void>> handle(@PathVariable Long id) {
        return CompletableFuture
                .runAsync(() -> colorDeactivateService.execute(id), executor)
                .thenApply((result) -> ResponseEntity.noContent().build());
    }
}
