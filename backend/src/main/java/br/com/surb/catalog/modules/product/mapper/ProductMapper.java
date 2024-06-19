package br.com.surb.catalog.modules.product.mapper;

import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.request.ProductRequest;
import br.com.surb.catalog.modules.product.response.ProductResponse;

public final class ProductMapper {
    public static ProductResponse toDTO(Product entity) {
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

    public static Product toEntity(ProductResponse response) {
        return new Product(
                response.id(),
                response.name(),
                response.description(),
                response.price(),
                response.imgUri(),
                response.createdAt(),
                response.updatedAt(),
                response.active()
        );
    }

    public static Product toEntity(ProductRequest request) {
        Product entity = new Product();
        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setImgUri(request.imgUri());
        return entity;
    }
}
