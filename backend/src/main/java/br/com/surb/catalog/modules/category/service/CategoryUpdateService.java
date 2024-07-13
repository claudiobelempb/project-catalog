package br.com.surb.catalog.modules.category.service;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.mapper.CategoryMapper;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.request.CategoryUpdateRequest;
import br.com.surb.catalog.modules.category.response.CategoryResponse;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CategoryUpdateService {
    private final CategoryRepository categoryRepository;

    public CategoryUpdateService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryResponse execute(Long id, CategoryUpdateRequest request) {
        try {
            Objects.requireNonNull(id);
            Category entity = CategoryMapper.toUpdateRequest(id, request, categoryRepository);
            entity = categoryRepository.save(entity);
            return CategoryMapper.toResponse(entity);
        } catch (EntityNotFoundException e) {
            throw new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id);
        }
    }
}
