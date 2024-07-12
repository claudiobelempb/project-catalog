package br.com.surb.catalog.modules.category.request;

import java.io.Serial;
import java.io.Serializable;

public record CategoryRequest(
        Long id,
        String name,
        boolean active
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4974682369660931936L;
}
