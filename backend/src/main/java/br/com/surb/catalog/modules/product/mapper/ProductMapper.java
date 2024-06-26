package br.com.surb.catalog.modules.product.mapper;

import br.com.surb.catalog.modules.category.response.CategoryResponseCustom;
import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.request.ProductRequestCustom;
import br.com.surb.catalog.modules.product.response.ProductResponse;
import br.com.surb.catalog.modules.product.response.ProductResponseCustom;

import java.util.stream.Collectors;

public final class ProductMapper {
    public static ProductResponse toResponse(Product entity) {
        if (entity == null) {
            return null;
        }
        return new ProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImgUri()
        );
    }

    public static Product toRequest(ProductResponse response) {
        if (response == null) {
            return null;
        }
        Product entity = new Product();
        entity.setId(response.id());
        entity.setName(response.name());
        entity.setDescription(response.description());
        entity.setPrice(response.price());
        entity.setImgUri(response.imgUri());

        return entity;
    }

    public static ProductResponseCustom toCustomResponse(Product entity) {
        if (entity == null) {
            return null;
        }
        return new ProductResponseCustom(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImgUri(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive(),
                entity.getCategories().stream().map(c -> new CategoryResponseCustom(c.getId(), c.getName())).collect(Collectors.toSet())
        );
    }

    public static Product toCustomRequest(
            Product entity,
            ProductRequestCustom request
    ) {
        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setPrice(request.price());
        entity.setImgUri(request.imgUri());

        return entity;
    }


}
