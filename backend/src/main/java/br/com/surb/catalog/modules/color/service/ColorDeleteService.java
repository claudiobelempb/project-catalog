package br.com.surb.catalog.modules.color.service;

import br.com.surb.catalog.modules.color.repository.ColorRepository;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppDataIntegrityViolationException;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ColorDeleteService {
    private final ColorRepository colorRepository;

    public ColorDeleteService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public void execute(Long id) {
        try {
            if (colorRepository.existsByIdAndActive(id, true) || !colorRepository.existsByIdAndActive(id, false)) {
                throw new AppEntityNotFoundException(ExceptionConstants.ENTITY_DEACTIVATE + id);
            }
            colorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AppDataIntegrityViolationException(ExceptionConstants.DATA_INTEGRITY_VIOLATION + id);
        }
    }
}
