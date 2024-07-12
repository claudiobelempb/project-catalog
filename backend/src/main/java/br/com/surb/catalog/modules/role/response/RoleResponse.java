package br.com.surb.catalog.modules.role.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record RoleResponse(
        Long id,
        String authority,
        Instant createdAt,
        Instant updatedAt,
        boolean active
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 4426748122162357175L;

}
