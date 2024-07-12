package br.com.surb.catalog.modules.role.response;

import java.io.Serial;
import java.io.Serializable;

public record RoleCustomResponse(
        Long id,
        String authority,
        boolean active
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -6722169205527090539L;
}
