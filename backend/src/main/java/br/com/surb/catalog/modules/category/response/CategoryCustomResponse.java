package br.com.surb.catalog.modules.category.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record CategoryCustomResponse(
        Long id,
        String name,
        boolean active
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4974682369660931936L;
}
