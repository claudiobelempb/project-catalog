package br.com.surb.catalog.modules.category.response;

import br.com.surb.catalog.modules.product.response.ProductCustomResponse;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public record CategoryProductResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updateAt,
        boolean active,
        Set<ProductCustomResponse> products
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4974682369660931936L;
}
