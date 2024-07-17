package br.com.surb.catalog.modules.color.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record ColorResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt,
        boolean active
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 3760509488386269409L;
}
