package br.com.surb.catalog.modules.color.repository;

import br.com.surb.catalog.modules.color.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
//    @Query(nativeQuery = true, value = "SELECT c.id, c.active FROM tb_color c WHERE c.id = :id AND c.active = :active")
    boolean existsByIdAndActive(Long id, Boolean active);

    Optional<Color> findByIdAndActive(Long id, Boolean active);

    @Query(nativeQuery = true, value = "SELECT c.name FROM tb_color c WHERE c.name = :name AND c.active = :active")
    Color findByNameAndActive(String name, Boolean active);
}
