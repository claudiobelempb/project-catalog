package br.com.surb.catalog.modules.user.request;

import br.com.surb.catalog.modules.role.response.RoleCustomResponse;
import br.com.surb.catalog.shared.constants.ValidatorConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public record UserRoleRequest(
        @Size(min = 5, max = 16, message = ValidatorConstants.REQUIRED_SIZE_16)
        @NotBlank(message = ValidatorConstants.REQUIRED_FIELD)
        String firstName,
        @NotBlank(message = ValidatorConstants.REQUIRED_FIELD)
        String lastName,
        @Email(message = ValidatorConstants.REQUIRED_EMAIL)
        @NotBlank(message = ValidatorConstants.REQUIRED_FIELD)
        String email,
        String password,
        Set<RoleCustomResponse> roles
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 3731486260201120621L;
}
