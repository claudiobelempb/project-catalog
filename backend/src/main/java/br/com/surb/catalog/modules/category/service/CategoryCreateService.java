package br.com.surb.catalog.modules.category.service;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.mapper.CategoryMapper;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.response.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
public class CategoryCreateService {
    private final CategoryRepository categoryRepository;

    public CategoryCreateService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse execute(CategoryResponse dto) {
        Category entity = CategoryMapper.toEntity(dto);
        entity = categoryRepository.save(entity);
        return CategoryMapper.toDTO(entity);
    }
}
