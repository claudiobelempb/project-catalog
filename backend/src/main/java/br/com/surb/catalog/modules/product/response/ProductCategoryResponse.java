package br.com.surb.catalog.modules.product.response;

import br.com.surb.catalog.modules.category.response.CategoryCustomResponse;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public record ProductCategoryResponse(
        Long id,
        String name,
        String description,
        Double price,
        String imgUri,
        Instant createdAt,
        Instant updateAt,
        boolean active,
        Set<CategoryCustomResponse> categories
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -4102717012546057697L;
}
