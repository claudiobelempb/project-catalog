package br.com.surb.catalog.modules.user.service;

import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.mapper.UserMapper;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.request.UserCreateRequest;
import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCreateService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserCreateService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserRoleResponse execute(UserCreateRequest request) {
        User entity = UserMapper.toCreateRequest(request, roleRepository);
        entity.setPassword(passwordEncoder.encode(request.password()));
        entity = userRepository.save(entity);
        return UserMapper.toCustomResponse(entity);
    }
}
