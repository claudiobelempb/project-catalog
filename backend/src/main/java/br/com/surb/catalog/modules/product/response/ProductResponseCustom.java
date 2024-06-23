package br.com.surb.catalog.modules.product.response;

import br.com.surb.catalog.modules.category.response.CategoryResponseCustom;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public record ProductResponseCustom(
        Long id,
        String name,
        String description,
        Double price,
        String imgUri,
        Instant createdAt,
        Instant updatedAt,
        boolean active,
        Set<CategoryResponseCustom> categories
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -6654711856644401040L;
}
