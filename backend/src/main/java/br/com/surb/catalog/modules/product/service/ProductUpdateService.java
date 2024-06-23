package br.com.surb.catalog.modules.product.service;

import br.com.surb.catalog.modules.category.entity.Category;
import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.category.response.CategoryResponse;
import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.mapper.ProductMapper;
import br.com.surb.catalog.modules.product.repository.ProductRepository;
import br.com.surb.catalog.modules.product.request.ProductRequestCustom;
import br.com.surb.catalog.modules.product.response.ProductResponse;
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
    public ProductResponse execute(Long id, ProductRequestCustom request) {
        try {
            Product entity = productRepository.getReferenceById(id);
            Product customRequest = ProductMapper.toCustomRequest(
                    entity,
                    request
            );

            customRequest.getCategories().clear();
            for (CategoryResponse c : request.categories()) {
                Category category = categoryRepository.getReferenceById(c.id());
                customRequest.getCategories().add(category);
            }

            productRepository.save(customRequest);

            return ProductMapper.toResponse(customRequest);
        } catch (EntityNotFoundException e) {
            throw new AppResourceNotFondExecption(AppExceptionConstants.RESOURCE_NOT_FOUND);
        }
    }
}
