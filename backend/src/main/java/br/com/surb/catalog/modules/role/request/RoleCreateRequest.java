package br.com.surb.catalog.modules.role.request;

import br.com.surb.catalog.modules.role.validation.RoleCreateValidation.RoleCreateValid;
import br.com.surb.catalog.modules.user.response.UserCustomResponse;
import br.com.surb.catalog.shared.constants.ValidatorConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@RoleCreateValid
public record RoleCreateRequest(
        @Size(min = 5, max = 16, message = ValidatorConstants.REQUIRED_SIZE_16)
        @NotBlank(message = ValidatorConstants.REQUIRED_FIELD)
        String authority,
        Set<UserCustomResponse> users
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -7140923608762172072L;
}
