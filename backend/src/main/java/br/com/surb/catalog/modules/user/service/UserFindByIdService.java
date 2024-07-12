package br.com.surb.catalog.modules.user.service;

import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.mapper.UserMapper;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import br.com.surb.catalog.shared.constants.AppExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserFindByIdService {
    private final UserRepository userRepository;

    public UserFindByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserRoleResponse execute(Long id) {
        Objects.requireNonNull(id);
        User entity = userRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new AppEntityNotFoundException(AppExceptionConstants.ENTITY_NOT_FOUND + id));
        return UserMapper.toCustomResponse(entity);
    }
}
