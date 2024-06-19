package br.com.surb.catalog.modules.product.request;

import br.com.surb.catalog.modules.category.entity.Category;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public record ProductRequestCustom(
        Long id,
        String name,
        String description,
        Double price,
        String imgUri,
        boolean active,
        Set<Category> categories
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -6654711856644401040L;
}
