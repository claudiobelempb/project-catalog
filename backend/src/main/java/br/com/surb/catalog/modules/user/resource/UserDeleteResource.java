package br.com.surb.catalog.modules.user.resource;

import br.com.surb.catalog.modules.user.service.UserDeleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserDeleteResource {
    private final UserDeleteService userDeleteService;

    public UserDeleteResource(UserDeleteService userDeleteService) {
        this.userDeleteService = userDeleteService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> handle(@PathVariable Long id) {
        userDeleteService.execute(id);
        return ResponseEntity.noContent().build();
    }
}
