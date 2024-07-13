package br.com.surb.catalog.modules.role.resource;

import br.com.surb.catalog.modules.role.response.RoleResponse;
import br.com.surb.catalog.modules.role.service.RoleFindAllPageService;
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
@RequestMapping(value = "/roles")
public class RoleFindAllPageResource {
    private final RoleFindAllPageService roleFindAllPageService;
    private final Executor executor;

    public RoleFindAllPageResource(RoleFindAllPageService roleFindAllPageService, Executor executor) {
        this.roleFindAllPageService = roleFindAllPageService;
        this.executor = executor;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Page<RoleResponse>>> handle(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "authority") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return CompletableFuture.supplyAsync(() -> roleFindAllPageService.execute(pageRequest), executor)
                .thenApply((r) -> ResponseEntity.ok().body(r));
    }
}
