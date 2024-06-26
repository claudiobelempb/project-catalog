package br.com.surb.catalog.modules.product.service;

import br.com.surb.catalog.modules.product.entity.Product;
import br.com.surb.catalog.modules.product.mapper.ProductMapper;
import br.com.surb.catalog.modules.product.repository.ProductRepository;
import br.com.surb.catalog.modules.product.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductFindAllService {
    private final ProductRepository productRepository;

    public ProductFindAllService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> execute(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map((r) -> ProductMapper.toResponse(r));
    }
}
