package br.com.surb.catalog.modules.role.service;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.mapper.RoleMapper;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.response.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleFindAllPageService {
    private final RoleRepository roleRepository;

    public RoleFindAllPageService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public Page<RoleResponse> execute(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        return roles.map((r) -> RoleMapper.toResponse(r));
    }
}
