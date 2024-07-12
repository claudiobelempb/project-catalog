package br.com.surb.catalog.modules.user.resource;

import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import br.com.surb.catalog.modules.user.service.UserFindAllPageService;
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
@RequestMapping(value = "/users")
public class UserFindAllPageResource {
    private final UserFindAllPageService userFindAllPageService;
    private final Executor executor;

    public UserFindAllPageResource(UserFindAllPageService userFindAllPageService, Executor executor) {
        this.userFindAllPageService = userFindAllPageService;
        this.executor = executor;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Page<UserRoleResponse>>> handle(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "firstName") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return CompletableFuture.supplyAsync(() -> userFindAllPageService.execute(pageRequest), executor)
                .thenApply((r) -> ResponseEntity.ok().body(r));
    }
}
