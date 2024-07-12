package br.com.surb.catalog.modules.user.service;

import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.shared.constants.ExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserDeactivateService {
    private final UserRepository userRepository;

    public UserDeactivateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void execute(Long id) {
        Objects.requireNonNull(id);
        User entity = userRepository
                .findByIdAndActive(id, true)
                .orElseThrow(() -> new AppEntityNotFoundException(ExceptionConstants.ENTITY_NOT_FOUND + id));
        entity.setActive(false);
        userRepository.save(entity);
    }
}
