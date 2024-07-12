package br.com.surb.catalog.modules.user.service;

import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.shared.constants.AppExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppDataIntegrityViolationException;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteService {
    private final UserRepository userRepository;

    public UserDeleteService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Long id) {
        try {
            if (userRepository.existsByIdAndActive(id, true) || !userRepository.existsByIdAndActive(id, false)) {
                throw new AppEntityNotFoundException(AppExceptionConstants.ENTITY_NOT_FOUND);
            }
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AppDataIntegrityViolationException(AppExceptionConstants.DATA_INTEGRITY_VIOLATION);
        }
    }
}
