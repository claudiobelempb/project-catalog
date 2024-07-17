package br.com.surb.catalog.modules.color.service;

import br.com.surb.catalog.modules.color.entity.Color;
import br.com.surb.catalog.modules.color.repository.ColorRepository;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ColorActiveService {
    private final ColorRepository colorRepository;

    public ColorActiveService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Transactional
    public void execute(Long id) {
        Objects.requireNonNull(id);
        Color entity = colorRepository
                .findByIdAndActive(id, false)
                .orElseThrow(() -> new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id));
        entity.setActive(true);
        colorRepository.save(entity);
    }
}
