package br.com.surb.catalog.modules.category.response;

import java.io.Serial;
import java.io.Serializable;

public record CategoryCreateResponse(
        Long id,
        String name
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4974682369660931936L;
}
