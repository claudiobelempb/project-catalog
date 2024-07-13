package br.com.surb.catalog.modules.user.service;

import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.mapper.UserMapper;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.request.UserUpdateRequest;
import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsResource.AppResourceNotFondExecption;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUpdateService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserUpdateService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public UserRoleResponse execute(Long id, UserUpdateRequest request) {
        try {
            User entity = UserMapper.toUpdateRequest(id, request, userRepository, roleRepository);
            userRepository.save(entity);
            return UserMapper.toCustomResponse(entity);
        } catch (EntityNotFoundException e) {
            throw new AppResourceNotFondExecption(ExceptionConstants.RESOURCE_NOT_FOUND);
        }
    }

}
