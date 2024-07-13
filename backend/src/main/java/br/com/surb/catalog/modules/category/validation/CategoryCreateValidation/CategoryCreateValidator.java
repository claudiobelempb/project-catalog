package br.com.surb.catalog.modules.category.validation.CategoryCreateValidation;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.request.CategoryCreateRequest;
import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.request.RoleCreateRequest;
import br.com.surb.catalog.shared.exeptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

import static br.com.surb.catalog.shared.constants.ValidatorConstants.REQUIRED_NAME_EXIST;

public class CategoryCreateValidator implements ConstraintValidator<CategoryCreateValid, CategoryCreateRequest> {

    private final CategoryRepository categoryRepository;

    public CategoryCreateValidator( CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialize(CategoryCreateValid ann) {
    }

    @Override
    public boolean isValid(CategoryCreateRequest request, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        Category category = categoryRepository.findByNameAndActive(request.name(), true);
        if (category != null) {
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
