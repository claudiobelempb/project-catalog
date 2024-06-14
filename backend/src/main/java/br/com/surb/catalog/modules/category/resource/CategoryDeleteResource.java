package br.com.surb.catalog.modules.category.resource;

import br.com.surb.catalog.modules.category.service.CategoryDeleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryDeleteResource {
    private final CategoryDeleteService categoryDeleteService;

    public CategoryDeleteResource(CategoryDeleteService categoryDeleteService) {
        this.categoryDeleteService = categoryDeleteService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> handle(@PathVariable Long id) {
        categoryDeleteService.execute(id);
        return ResponseEntity.noContent().build();
    }
}
