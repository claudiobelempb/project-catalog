package br.com.surb.catalog.modules.product.repository;

import br.com.surb.catalog.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByIdAndActive(Long id, Boolean active);

    Optional<Product> findByIdAndActive(Long id, Boolean active);

    Optional<Product> findByNameAndActive(String name, Boolean active);
}
