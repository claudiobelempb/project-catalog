package br.com.surb.catalog.modules.product.service;

import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.mapper.ProductMapper;
import br.com.surb.catalog.modules.product.repository.ProductRepository;
import br.com.surb.catalog.modules.product.request.ProductCategoryRequest;
import br.com.surb.catalog.modules.product.response.ProductCategoryResponse;
import br.com.surb.catalog.shared.constants.AppExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsResource.AppResourceNotFondExecption;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductUpdateService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductUpdateService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ProductCategoryResponse execute(Long id, ProductCategoryRequest request) {
        try {
            Product entity = ProductMapper.toUpdateRequest(id, request, productRepository, categoryRepository);
            entity = productRepository.save(entity);
            return ProductMapper.toCustomResponse(entity);
        } catch (EntityNotFoundException e) {
            throw new AppResourceNotFondExecption(AppExceptionConstants.RESOURCE_NOT_FOUND);
        }
    }
}
