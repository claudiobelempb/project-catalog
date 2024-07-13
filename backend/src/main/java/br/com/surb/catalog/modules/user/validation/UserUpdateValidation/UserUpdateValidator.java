package br.com.surb.catalog.modules.user.validation.UserUpdateValidation;

import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.request.UserUpdateRequest;
import br.com.surb.catalog.shared.exeptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

import static br.com.surb.catalog.shared.constants.ValidatorConstants.REQUIRED_EMAIL_EXIST;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateRequest> {

    private final UserRepository userRepository;

    public UserUpdateValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateRequest request, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        User userEmail = userRepository.findByEmail(request.email());
        User userName = userRepository.findByFirstName(request.firstName());
        if (userEmail != null) {
            list.add(new FieldMessage("email", REQUIRED_EMAIL_EXIST));
        }

        /*if (userName != null) {
            list.add(new FieldMessage("nome", REQUIRED_NAME_EXIST));
        }*/

        for (FieldMessage error : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(error.message()).addPropertyNode(error.fieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
