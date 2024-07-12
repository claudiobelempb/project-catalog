package br.com.surb.catalog.modules.product.service;

import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.mapper.ProductMapper;
import br.com.surb.catalog.modules.product.repository.ProductRepository;
import br.com.surb.catalog.modules.product.response.ProductCategoryResponse;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ProductFindByIdService {
    private final ProductRepository productRepository;

    public ProductFindByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductCategoryResponse execute(Long id) {
        Objects.requireNonNull(id);
        Product entity = productRepository.findByIdAndActive(id, true).orElseThrow(() -> new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id));
        return ProductMapper.toCustomResponse(entity);
    }
}
