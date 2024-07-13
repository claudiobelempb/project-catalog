package br.com.surb.catalog.modules.user.validation.UserCreateValidation;

import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.request.UserCreateRequest;
import br.com.surb.catalog.shared.exeptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

import static br.com.surb.catalog.shared.constants.ValidatorConstants.REQUIRED_EMAIL_EXIST;

public class UserCreateValidator implements ConstraintValidator<UserCreateValid, UserCreateRequest> {

    private final UserRepository userRepository;

    public UserCreateValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserCreateValid ann) {
    }

    @Override
    public boolean isValid(UserCreateRequest request, ConstraintValidatorContext context) {

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
