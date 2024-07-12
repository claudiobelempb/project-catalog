package br.com.surb.catalog.modules.role.service;

import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.shared.constants.AppExceptionConstants;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppDataIntegrityViolationException;
import br.com.surb.catalog.shared.exeptions.ExeptionsService.AppEntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RoleDeleteService {
    private final RoleRepository roleRepository;

    public RoleDeleteService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void execute(Long id) {
        try {
            if (roleRepository.existsByIdAndActive(id, true) || !roleRepository.existsByIdAndActive(id, false)) {
                throw new AppEntityNotFoundException(AppExceptionConstants.ENTITY_NOT_FOUND);
            }
            roleRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AppDataIntegrityViolationException(AppExceptionConstants.DATA_INTEGRITY_VIOLATION);
        }
    }
}
