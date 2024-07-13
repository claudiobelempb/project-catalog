package br.com.surb.catalog.modules.role.validation.RoleCreateValidation;

import br.com.surb.catalog.shared.constants.ValidatorConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleCreateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface RoleCreateValid {
    String message() default ValidatorConstants.VIOLATION_ERROR;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
