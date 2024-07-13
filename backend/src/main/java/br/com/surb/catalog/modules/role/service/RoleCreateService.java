package br.com.surb.catalog.modules.role.service;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.mapper.RoleMapper;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.request.RoleCreateRequest;
import br.com.surb.catalog.modules.role.response.RoleResponse;
import org.springframework.stereotype.Service;

@Service
public class RoleCreateService {
    private final RoleRepository roleRepository;

    public RoleCreateService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponse execute(RoleCreateRequest request) {
        Role entity = RoleMapper.toCreateRequest(request);
        entity = roleRepository.save(entity);
        return RoleMapper.toResponse(entity);
    }
}
