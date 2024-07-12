package br.com.surb.catalog.modules.user.service;

import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.mapper.UserMapper;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.response.UserRoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFindAllPageService {
    private final UserRepository userRepository;

    public UserFindAllPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserRoleResponse> execute(Pageable pageable) {
        Page<User> roles = userRepository.findAll(pageable);
        return roles.map((r) -> UserMapper.toCustomResponse(r));
    }
}
