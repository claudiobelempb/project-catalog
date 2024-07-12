package br.com.surb.catalog.modules.user.request;

import br.com.surb.catalog.shared.constants.ValidatorConstants;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

public record UserRequest(
        @NotBlank(message = ValidatorConstants.REQUIRED_FIELD)
        String firstName,
        @NotBlank(message = ValidatorConstants.REQUIRED_FIELD)
        String lastName,
        String email,
        String password
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 3731486260201120621L;
}
