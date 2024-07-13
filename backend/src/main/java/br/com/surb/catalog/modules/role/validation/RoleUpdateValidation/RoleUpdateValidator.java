package br.com.surb.catalog.modules.role.validation.RoleUpdateValidation;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.request.RoleUpdateRequest;
import br.com.surb.catalog.shared.exeptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

import static br.com.surb.catalog.shared.constants.ValidatorConstants.REQUIRED_NAME_EXIST;

public class RoleUpdateValidator implements ConstraintValidator<RoleUpdateValid, RoleUpdateRequest> {

    private final RoleRepository roleRepository;

    public RoleUpdateValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialize(RoleUpdateValid ann) {
    }

    @Override
    public boolean isValid(RoleUpdateRequest request, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        Role role = roleRepository.findByAuthority(request.authority());
        if (role != null) {
            list.add(new FieldMessage("nome", REQUIRED_NAME_EXIST));
        }

        for (FieldMessage error : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(error.message()).addPropertyNode(error.fieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
