package br.com.surb.catalog.modules.user.response;

import java.io.Serial;
import java.io.Serializable;

public record UserCustomResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        boolean active
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -5567409035322515876L;
}
