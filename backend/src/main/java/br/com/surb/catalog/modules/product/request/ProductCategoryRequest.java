package br.com.surb.catalog.modules.product.request;

import br.com.surb.catalog.modules.category.response.CategoryCustomResponse;
import br.com.surb.catalog.modules.category.response.CategoryProductResponse;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public record ProductCategoryRequest(
        String name,
        String description,
        Double price,
        String imgUri,
        Set<CategoryCustomResponse> categories
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -6654711856644401040L;
}
