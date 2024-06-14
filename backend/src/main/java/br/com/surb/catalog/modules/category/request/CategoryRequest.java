package br.com.surb.catalog.modules.category.request;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record CategoryRequest(
        String name
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4974682369660931936L;
}
