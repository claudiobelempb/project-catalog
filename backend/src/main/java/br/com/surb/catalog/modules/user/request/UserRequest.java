package br.com.surb.catalog.modules.user.request;

import java.io.Serial;
import java.io.Serializable;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 3731486260201120621L;
}
