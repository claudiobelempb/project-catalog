package br.com.surb.catalog.modules.category.mapper;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.response.CategoryResponse;

public final class CategoryMapper {
    public static CategoryResponse toDTO(Category entity) {
        return new CategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdateAt(),
                entity.isActive()
        );
    }

    public static Category toEntity(CategoryResponse response) {
        return new Category(
                response.id(),
                response.name(),
                response.createdAt(),
                response.updateAt(),
                response.active()
        );

    }
}
