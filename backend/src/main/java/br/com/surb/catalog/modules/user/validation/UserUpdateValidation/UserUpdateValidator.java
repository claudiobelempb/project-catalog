package br.com.surb.catalog.modules.user.validation.UserUpdateValidation;

import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.request.UserUpdateRequest;
import br.com.surb.catalog.shared.exeptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.surb.catalog.shared.constants.ValidatorConstants.REQUIRED_EMAIL_EXIST;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateRequest> {

    private final UserRepository userRepository;
    private final HttpServletRequest httpServletRequest;

    public UserUpdateValidator(UserRepository userRepository, HttpServletRequest httpServletRequest) {
        this.userRepository = userRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateRequest request, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var attribute = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(attribute.get("id"));


        List<FieldMessage> list = new ArrayList<>();

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        User user = userRepository.findByEmail(request.email());
        if (user != null && userId != user.getId()) {
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
