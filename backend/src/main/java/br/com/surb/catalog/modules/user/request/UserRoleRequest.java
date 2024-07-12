package br.com.surb.catalog.modules.user.request;

import br.com.surb.catalog.modules.role.response.RoleCustomResponse;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public record UserRoleRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Set<RoleCustomResponse> roles
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 3731486260201120621L;
}
