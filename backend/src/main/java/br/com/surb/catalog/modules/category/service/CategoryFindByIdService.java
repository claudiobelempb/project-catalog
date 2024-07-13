package br.com.surb.catalog.modules.category.service;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.mapper.CategoryMapper;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.response.CategoryProductResponse;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CategoryFindByIdService {
    private final CategoryRepository categoryRepository;

    public CategoryFindByIdService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public CategoryProductResponse execute(Long id) {
        Objects.requireNonNull(id);
        Category entity = categoryRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id));
        return CategoryMapper.toCategoryProductResponse(entity);
    }
}
