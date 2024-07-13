package br.com.surb.catalog.modules.category.mapper;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.request.CategoryCreateRequest;
import br.com.surb.catalog.modules.category.request.CategoryRequest;
import br.com.surb.catalog.modules.category.request.CategoryUpdateRequest;
import br.com.surb.catalog.modules.category.response.CategoryProductResponse;
import br.com.surb.catalog.modules.category.response.CategoryResponse;
import br.com.surb.catalog.modules.product.response.ProductCustomResponse;

import java.util.stream.Collectors;

public final class CategoryMapper {

    public static CategoryResponse toResponse(Category entity) {
        return new CategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive()
        );
    }

    public static CategoryProductResponse toCategoryProductResponse(Category entity) {
        return new CategoryProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive(),
                entity.getProducts().stream().map(r -> new ProductCustomResponse(
                        r.getId(),
                        r.getName(),
                        r.isActive()
                )).collect(Collectors.toSet()
                )
        );
    }

    public static Category toCreateRequest(CategoryCreateRequest request) {
        return Category.builder().name(request.name()).build();
    }

    public static Category toUpdateRequest(Long id, CategoryUpdateRequest request, CategoryRepository repository) {
        Category entity = repository.getReferenceById(id);
        entity.setName(request.name());
        return entity;
    }

}
