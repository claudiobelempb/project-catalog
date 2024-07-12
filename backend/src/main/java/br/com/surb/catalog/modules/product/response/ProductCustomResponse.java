package br.com.surb.catalog.modules.product.response;

import java.io.Serial;
import java.io.Serializable;

public record ProductCustomResponse(
        Long id,
        String name,
        boolean active
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -4102717012546057697L;
}
