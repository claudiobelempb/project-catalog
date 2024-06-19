package br.com.surb.catalog.modules.product.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        String imgUri,
        Instant createdAt,
        Instant updatedAt,
        boolean active
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -6654711856644401040L;
}
