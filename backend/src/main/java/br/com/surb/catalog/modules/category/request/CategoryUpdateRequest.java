package br.com.surb.catalog.modules.category.request;

import br.com.surb.catalog.modules.category.validation.CategoryUpdateValidation.CategoryUpdateValid;
import br.com.surb.catalog.modules.product.response.ProductCustomResponse;
import br.com.surb.catalog.shared.constants.ValidatorConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@CategoryUpdateValid
public record CategoryUpdateRequest(
        @Size(min = 5, max = 16, message = ValidatorConstants.REQUIRED_SIZE_16)
        @NotBlank(message = ValidatorConstants.REQUIRED_FIELD)
        String name,
        boolean active,
        List<ProductCustomResponse> products
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4974682369660931936L;
}
