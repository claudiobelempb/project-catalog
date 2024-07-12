package br.com.surb.catalog.modules.user.service;

import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.mapper.UserMapper;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.request.UserRoleRequest;
import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCreateService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserCreateService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public UserRoleResponse execute(UserRoleRequest request) {
        User entity = UserMapper.toCreateRequest(request, roleRepository);
        entity = userRepository.save(entity);
        return UserMapper.toCustomResponse(entity);
    }
}
