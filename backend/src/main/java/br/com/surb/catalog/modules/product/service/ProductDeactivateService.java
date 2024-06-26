package br.com.surb.catalog.modules.product.service;

import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.repository.ProductRepository;
import br.com.surb.catalog.shared.constants.AppExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ProductDeactivateService {
    private final ProductRepository productRepository;

    public ProductDeactivateService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void execute(Long id) {
        Objects.requireNonNull(id);
        Product entity = productRepository
                .findByIdAndActive(id, true)
                .orElseThrow(() -> new AppEntityNotFoundException(AppExceptionConstants.ENTITY_NOT_FOUND + id));
        entity.setActive(false);
        productRepository.save(entity);
    }
}
