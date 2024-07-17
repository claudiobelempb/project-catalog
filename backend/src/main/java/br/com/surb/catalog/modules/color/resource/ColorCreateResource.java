package br.com.surb.catalog.modules.color.resource;

import br.com.surb.catalog.modules.color.request.ColorCreateRequest;
import br.com.surb.catalog.modules.color.response.ColorResponse;
import br.com.surb.catalog.modules.color.service.ColorCreateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping(value = "/colors")
public class ColorCreateResource {
    private final ColorCreateService roleCreateService;
    private final Executor executor;

    public ColorCreateResource(ColorCreateService roleCreateService, Executor executor) {
        this.roleCreateService = roleCreateService;
        this.executor = executor;
    }


    @PostMapping
    public CompletableFuture<ResponseEntity<ColorResponse>> handle(@Valid @RequestBody ColorCreateRequest request) {
        ColorResponse response = roleCreateService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return supplyAsync(() -> response, executor).thenApply((__) -> ResponseEntity.created(uri).body(response));
    }
}
