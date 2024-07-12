package br.com.surb.catalog.modules.role.request;

import java.io.Serial;
import java.io.Serializable;

public record RoleRequest(
        String authority
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -7140923608762172072L;
}
