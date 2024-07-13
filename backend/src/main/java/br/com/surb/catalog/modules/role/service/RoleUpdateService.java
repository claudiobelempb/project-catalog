package br.com.surb.catalog.modules.role.service;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.mapper.RoleMapper;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.request.RoleUpdateRequest;
import br.com.surb.catalog.modules.role.response.RoleResponse;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleUpdateService {
    private final RoleRepository roleRepository;

    public RoleUpdateService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleResponse execute(Long id, RoleUpdateRequest request) {
        try {
            Role entity = RoleMapper.toUpdateRequest(id, request, roleRepository);
            entity = roleRepository.save(entity);
            return RoleMapper.toResponse(entity);
        } catch (EntityNotFoundException e) {
            throw new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id);
        }
    }
}
