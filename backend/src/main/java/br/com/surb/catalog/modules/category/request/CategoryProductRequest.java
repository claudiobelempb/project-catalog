package br.com.surb.catalog.modules.category.request;

import br.com.surb.catalog.modules.product.response.ProductCustomResponse;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record CategoryProductRequest(
        Long id,
        String name,
        boolean active,
        List<ProductCustomResponse> products
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4974682369660931936L;
}
