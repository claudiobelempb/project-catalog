package br.com.surb.catalog.modules.color.response;

import java.io.Serial;
import java.io.Serializable;

public record ColorCustomResponse(
        Long id,
        String name,
        boolean active
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 8495980061682610018L;
}
