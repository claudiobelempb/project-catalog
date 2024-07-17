package br.com.surb.catalog.modules.color.service;

import br.com.surb.catalog.modules.color.entity.Color;
import br.com.surb.catalog.modules.color.mapper.ColorMapper;
import br.com.surb.catalog.modules.color.repository.ColorRepository;
import br.com.surb.catalog.modules.color.request.ColorCreateRequest;
import br.com.surb.catalog.modules.color.response.ColorResponse;
import org.springframework.stereotype.Service;

@Service
public class ColorCreateService {
    private final ColorRepository colorRepository;

    public ColorCreateService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }


    public ColorResponse execute(ColorCreateRequest request) {
        Color entity = ColorMapper.toCreateRequest(request);
        entity = colorRepository.save(entity);
        return ColorMapper.toResponse(entity);
    }
}
