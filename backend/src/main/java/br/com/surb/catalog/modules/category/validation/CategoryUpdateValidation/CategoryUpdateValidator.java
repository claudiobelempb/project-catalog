package br.com.surb.catalog.modules.category.validation.CategoryUpdateValidation;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.request.CategoryUpdateRequest;
import br.com.surb.catalog.shared.exeptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

import static br.com.surb.catalog.shared.constants.ValidatorConstants.REQUIRED_NAME_EXIST;

public class CategoryUpdateValidator implements ConstraintValidator<CategoryUpdateValid, CategoryUpdateRequest> {

    private final CategoryRepository categoryRepository;

    public CategoryUpdateValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void initialize(CategoryUpdateValid ann) {
    }

    @Override
    public boolean isValid(CategoryUpdateRequest request, ConstraintValidatorContext context) {

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
