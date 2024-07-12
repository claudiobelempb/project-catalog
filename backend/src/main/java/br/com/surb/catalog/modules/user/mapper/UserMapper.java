package br.com.surb.catalog.modules.user.mapper;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.response.RoleCustomResponse;
import br.com.surb.catalog.modules.user.entity.User;
import br.com.surb.catalog.modules.user.repository.UserRepository;
import br.com.surb.catalog.modules.user.request.UserRequest;
import br.com.surb.catalog.modules.user.request.UserRoleRequest;
import br.com.surb.catalog.modules.user.response.UserResponse;
import br.com.surb.catalog.modules.user.response.UserRoleResponse;

import java.util.stream.Collectors;

public final class UserMapper {

    public static UserResponse toResponse(User entity) {
        if (entity == null) {
            return null;
        }
        return new UserResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive()
        );
    }

    public static UserRoleResponse toCustomResponse(User entity) {
        if (entity == null) {
            return null;
        }
        return new UserRoleResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive(),
                entity.getRoles().stream().map(r -> new RoleCustomResponse(
                        r.getId(),
                        r.getAuthority(),
                        r.isActive()
                )).collect(Collectors.toSet())
        );
    }

    public static User toRequest(UserRequest request) {
        if (request.email() == null) {
            return null;
        }
        User entity = new User();
        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setEmail(request.email());
        return entity;
    }

    public static User toCreateRequest(UserRoleRequest request, RoleRepository repository) {
        if (request.email() == null) {
            return null;
        }
        User entity = new User();
        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setEmail(request.email());
        entity.setPassword(request.password());

        entity.getRoles().clear();
        for (RoleCustomResponse r : request.roles()) {
            Role role = repository.getReferenceById(r.id());
            entity.getRoles().add(role);
        }
        return entity;
    }

    public static User toUpdateRequest(
            Long id,
            UserRoleRequest request,
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        if (id == null) {
            return null;
        }
        User entity = userRepository.getReferenceById(id);
        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setEmail(request.email());
        entity.setPassword(request.password());

        entity.getRoles().clear();
        for (RoleCustomResponse r : request.roles()) {
            Role role = roleRepository.getReferenceById(r.id());
            entity.getRoles().add(role);
        }
        return entity;
    }

}
