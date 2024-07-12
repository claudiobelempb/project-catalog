package br.com.surb.catalog.modules.user.response;

import br.com.surb.catalog.modules.role.response.RoleCustomResponse;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

public record UserRoleResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Instant createdAt,
        Instant updatedAt,
        boolean active,
        Set<RoleCustomResponse> roles
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 4684121582820588652L;
}
