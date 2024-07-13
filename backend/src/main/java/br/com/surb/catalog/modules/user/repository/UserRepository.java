package br.com.surb.catalog.modules.user.repository;

import br.com.surb.catalog.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT id, active FROM tb_user c WHERE c.id = :id AND c.active = :active")
    boolean existsByIdAndActive(Long id, Boolean active);

    Optional<User> findByIdAndActive(Long id, Boolean active);

    User findByEmail(String email);

    User findByFirstName(String name);

    //Optional<User> findByNameAndActive(String name, Boolean active);
}
