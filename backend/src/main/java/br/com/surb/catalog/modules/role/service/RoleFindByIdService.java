package br.com.surb.catalog.modules.role.service;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.mapper.RoleMapper;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.response.RoleResponse;
import br.com.surb.catalog.modules.role.response.RoleUserResponse;
import br.com.surb.catalog.shared.constants.AppExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class RoleFindByIdService {
    private final RoleRepository roleRepository;

    public RoleFindByIdService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public RoleUserResponse execute(Long id) {
        Objects.requireNonNull(id);
        Role entity = roleRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new AppEntityNotFoundException(AppExceptionConstants.ENTITY_NOT_FOUND + id));
        return RoleMapper.toCustomResponse(entity);
    }
}
