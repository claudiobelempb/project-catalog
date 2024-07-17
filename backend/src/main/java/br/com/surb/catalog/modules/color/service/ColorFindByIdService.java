package br.com.surb.catalog.modules.color.service;

import br.com.surb.catalog.modules.color.entity.Color;
import br.com.surb.catalog.modules.color.mapper.ColorMapper;
import br.com.surb.catalog.modules.color.repository.ColorRepository;
import br.com.surb.catalog.modules.color.response.ColorCustomResponse;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ColorFindByIdService {
    private final ColorRepository colorRepository;

    public ColorFindByIdService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Transactional(readOnly = true)
    public ColorCustomResponse execute(Long id) {
        Objects.requireNonNull(id);
        Color entity = colorRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id));
        return ColorMapper.toCustomResponse(entity);
    }
}
