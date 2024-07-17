package br.com.surb.catalog.modules.color.resource;

import br.com.surb.catalog.modules.color.response.ColorResponse;
import br.com.surb.catalog.modules.color.service.ColorFindAllPageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(value = "/colors")
public class ColorFindAllPageResource {
    private final ColorFindAllPageService colorFindAllPageService;
    private final Executor executor;

    public ColorFindAllPageResource(ColorFindAllPageService colorFindAllPageService, Executor executor) {
        this.colorFindAllPageService = colorFindAllPageService;
        this.executor = executor;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Page<ColorResponse>>> handle(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return CompletableFuture.supplyAsync(() -> colorFindAllPageService.execute(pageRequest), executor)
                .thenApply((r) -> ResponseEntity.ok().body(r));
    }
}
