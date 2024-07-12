package br.com.surb.catalog.modules.product.request;

import java.io.Serial;
import java.io.Serializable;


public record ProductRequest(
        Long id,
        String name,
        String description,
        Double price,
        String imgUri
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -6654711856644401040L;
}
