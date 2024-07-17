package br.com.surb.catalog.modules.color.resource;

import br.com.surb.catalog.modules.color.service.ColorDeleteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/colors")
public class ColorDeleteResource {
    private final ColorDeleteService colorDeleteService;

    public ColorDeleteResource(ColorDeleteService colorDeleteService) {
        this.colorDeleteService = colorDeleteService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> handle(@PathVariable Long id) {
        colorDeleteService.execute(id);
        return ResponseEntity.noContent().build();
    }
}
