package br.com.surb.catalog.modules.role.repository;

import br.com.surb.catalog.modules.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByIdAndActive(Long id, Boolean active);

    Optional<Role> findByIdAndActive(Long id, Boolean active);

    Optional<Role> findByAuthorityAndActive(String name, Boolean active);

    Role findByAuthority(String authority);
}
