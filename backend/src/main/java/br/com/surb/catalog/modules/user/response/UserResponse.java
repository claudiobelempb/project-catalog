package br.com.surb.catalog.modules.user.response;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;


public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Instant createdAt,
        Instant updatedAt,
        boolean active
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 4684121582820588652L;


}
