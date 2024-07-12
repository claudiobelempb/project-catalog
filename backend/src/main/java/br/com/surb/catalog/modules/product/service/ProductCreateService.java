package br.com.surb.catalog.modules.product.service;

import br.com.surb.catalog.modules.category.repository.CategoryRepository;
import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.mapper.ProductMapper;
import br.com.surb.catalog.modules.product.repository.ProductRepository;
import br.com.surb.catalog.modules.product.request.ProductCategoryRequest;
import br.com.surb.catalog.modules.product.response.ProductCategoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCreateService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductCreateService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ProductCategoryResponse execute(ProductCategoryRequest request) {
        Product entity = ProductMapper.toCreateRequest(request, categoryRepository);
        entity = productRepository.save(entity);
        return ProductMapper.toCustomResponse(entity);
    }

}
