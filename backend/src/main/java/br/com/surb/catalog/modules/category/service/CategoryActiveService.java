package br.com.surb.catalog.modules.category.service;


import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.shared.constants.AppExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CategoryActiveService {

    private final CategoryRepository categoryRepository;

    public CategoryActiveService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void execute(Long id) {
        Objects.requireNonNull(id);
        Category entity = categoryRepository
                .findByIdAndActive(id, false)
                .orElseThrow(() -> new AppEntityNotFoundException(AppExceptionConstants.ENTITY_NOT_FOUND + id));
        entity.setActive(true);
        categoryRepository.save(entity);
    }
}
