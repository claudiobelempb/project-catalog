package br.com.surb.catalog.modules.category.repository;

import br.com.surb.catalog.modules.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query(nativeQuery = true, value = "SELECT * FROM tb_category c WHERE c.id = :id AND c.active = :active")
    boolean existsByIdAndActive(Long id, Boolean active);

    Optional<Category> findByIdAndActive(Long id, Boolean active);

    Optional<Category> findByNameAndActive(String name, Boolean active);
}
