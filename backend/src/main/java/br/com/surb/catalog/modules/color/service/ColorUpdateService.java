package br.com.surb.catalog.modules.color.service;

import br.com.surb.catalog.modules.color.entity.Color;
import br.com.surb.catalog.modules.color.mapper.ColorMapper;
import br.com.surb.catalog.modules.color.repository.ColorRepository;
import br.com.surb.catalog.modules.color.request.ColorUpdateRequest;
import br.com.surb.catalog.modules.color.response.ColorResponse;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColorUpdateService {
    private final ColorRepository colorRepository;

    public ColorUpdateService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Transactional
    public ColorResponse execute(Long id, ColorUpdateRequest request) {
        try {
            Color entity = ColorMapper.toUpdateRequest(id, request, colorRepository);
            entity = colorRepository.save(entity);
            return ColorMapper.toResponse(entity);
        } catch (EntityNotFoundException e) {
            throw new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id);
        }
    }
}
