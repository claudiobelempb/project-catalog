package br.com.surb.catalog.modules.color.mapper;

import br.com.surb.catalog.modules.color.entity.Color;
import br.com.surb.catalog.modules.color.repository.ColorRepository;
import br.com.surb.catalog.modules.color.request.ColorCreateRequest;
import br.com.surb.catalog.modules.color.request.ColorRequest;
import br.com.surb.catalog.modules.color.request.ColorUpdateRequest;
import br.com.surb.catalog.modules.color.response.ColorCustomResponse;
import br.com.surb.catalog.modules.color.response.ColorResponse;

public final class ColorMapper {
    public static ColorResponse toResponse(Color entity) {
        if (entity == null) {
            return null;
        }
        return new ColorResponse(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive()
        );
    }

    public static ColorCustomResponse toCustomResponse(Color entity) {
        return new ColorCustomResponse(
                entity.getId(),
                entity.getName(),
                entity.isActive()
        );
    }

    public static Color toRequest(ColorRequest request) {
        if (request.name() == null) {
            return null;
        }
        Color entity = new Color();
        entity.setName(request.name());
        return entity;
    }

    public static Color toCreateRequest(ColorCreateRequest request) {
        Color entity = new Color();
        entity.setName(request.name());
        return entity;
    }


    public static Color toUpdateRequest(
            Long id,
            ColorUpdateRequest request,
            ColorRepository colorRepository
    ) {
        Color entity = colorRepository.getReferenceById(id);
        entity.setName(request.name());
        return entity;
    }

}
