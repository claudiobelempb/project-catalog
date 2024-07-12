package br.com.surb.catalog.modules.role.response;

import br.com.surb.catalog.modules.user.response.UserCustomResponse;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public record RoleUserResponse(
        Long id,
        String authority,
        Instant createdAt,
        Instant updatedAt,
        boolean active,
        Set<UserCustomResponse> users
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 4426748122162357175L;

}
