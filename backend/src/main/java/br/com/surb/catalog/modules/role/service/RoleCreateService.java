package br.com.surb.catalog.modules.role.service;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.mapper.RoleMapper;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.request.RoleRequest;
import br.com.surb.catalog.modules.role.response.RoleResponse;
import org.springframework.stereotype.Service;

@Service
public class RoleCreateService {
    private final RoleRepository roleRepository;

    public RoleCreateService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponse execute(RoleRequest request) {
        Role entity = RoleMapper.toRequest(request);
        entity = roleRepository.save(entity);
        return RoleMapper.toResponse(entity);
    }
}
