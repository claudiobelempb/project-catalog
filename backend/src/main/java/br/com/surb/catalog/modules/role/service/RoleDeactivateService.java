package br.com.surb.catalog.modules.role.service;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleDeactivateService {
    private final RoleRepository roleRepository;

    public RoleDeactivateService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void execute(Long id) {
        Objects.requireNonNull(id);
        Role entity = roleRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id));
        entity.setActive(false);
        roleRepository.save(entity);
    }
}
