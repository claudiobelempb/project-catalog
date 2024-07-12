package br.com.surb.catalog.modules.category.service;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.mapper.CategoryMapper;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryFindAllService {
    private final CategoryRepository categoryRepository;

    public CategoryFindAllService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponse> execute(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map((r) -> CategoryMapper.toResponse(r));
    }
}
