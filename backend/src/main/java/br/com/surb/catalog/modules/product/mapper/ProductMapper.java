package br.com.surb.catalog.modules.product.mapper;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.response.CategoryCustomResponse;
import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.repository.ProductRepository;
import br.com.surb.catalog.modules.product.request.ProductCategoryRequest;
import br.com.surb.catalog.modules.product.request.ProductRequest;
import br.com.surb.catalog.modules.product.response.ProductCategoryResponse;
import br.com.surb.catalog.modules.product.response.ProductResponse;

import java.util.stream.Collectors;

public final class ProductMapper {
    public static ProductResponse toResponse(Product entity) {
        return new ProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImgUri(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive()
        );
    }

    public static ProductCategoryResponse toCustomResponse(Product entity) {
        return new ProductCategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImgUri(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive(),
                entity.getCategories().stream().map(r -> new CategoryCustomResponse(
                        r.getId(),
                        r.getName(),
                        r.isActive()
                )).collect(Collectors.toSet())
        );
    }

    public static Product toRequest(ProductRequest request) {
        Product entity = new Product();
        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setImgUri(request.imgUri());
        return entity;
    }

    public static Product toCreateRequest(ProductCategoryRequest request, CategoryRepository repository) {
        Product entity = new Product();
        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setImgUri(request.imgUri());

        entity.getCategories().clear();
        for (CategoryCustomResponse r : request.categories()) {
            Category category = repository.getReferenceById(r.id());
            entity.getCategories().add(category);
        }
        return entity;
    }

    public static Product toUpdateRequest(
            Long id,
            ProductCategoryRequest request,
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ) {
        Product entity = productRepository.getReferenceById(id);
        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setImgUri(request.imgUri());

        entity.getCategories().clear();
        for (CategoryCustomResponse r : request.categories()) {
            Category category = categoryRepository.getReferenceById(r.id());
            entity.getCategories().add(category);
        }
        return entity;
    }

}
