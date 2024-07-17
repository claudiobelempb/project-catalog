package br.com.surb.catalog.modules.category.service;

import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppDataIntegrityViolationException;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoryDeleteService {
    private final CategoryRepository categoryRepository;

    public CategoryDeleteService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void execute(Long id) {
        try {
            if (categoryRepository.existsByIdAndActive(id, true) || !categoryRepository.existsByIdAndActive(id, false)) {
                throw new AppEntityNotFoundException(ExceptionConstants.ENTITY_DEACTIVATE);
            }
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AppDataIntegrityViolationException(ExceptionConstants.DATA_INTEGRITY_VIOLATION);
        }
    }
}
