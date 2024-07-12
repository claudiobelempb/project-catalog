package br.com.surb.catalog.modules.product.service;

import br.com.surb.catalog.modules.product.repository.ProductRepository;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppDataIntegrityViolationException;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProductDeleteService {
    private final ProductRepository productRepository;

    public ProductDeleteService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Long id) {
        try {
            if (productRepository.existsByIdAndActive(id, true) || !productRepository.existsByIdAndActive(id, false)) {
                throw new AppEntityNotFoundException(ExceptionConstants.ENTITY_DEACTIVATE);
            }
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AppDataIntegrityViolationException(ExceptionConstants.DATA_INTEGRITY_VIOLATION);
        }
    }
}
