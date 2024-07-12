package br.com.surb.catalog.modules.role.request;

import br.com.surb.catalog.modules.user.response.UserCustomResponse;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public record RoleUserRequest(
        String authority,
        Set<UserCustomResponse> users
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -7140923608762172072L;
}
