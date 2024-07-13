package br.com.surb.catalog.modules.role.mapper;

import br.com.surb.catalog.modules.role.entity.Role;
import br.com.surb.catalog.modules.role.repository.RoleRepository;
import br.com.surb.catalog.modules.role.request.RoleCreateRequest;
import br.com.surb.catalog.modules.role.request.RoleRequest;
import br.com.surb.catalog.modules.role.request.RoleUpdateRequest;
import br.com.surb.catalog.modules.role.response.RoleResponse;
import br.com.surb.catalog.modules.role.response.RoleUserResponse;
import br.com.surb.catalog.modules.user.response.UserCustomResponse;

import java.util.stream.Collectors;

public final class RoleMapper {
    public static RoleResponse toResponse(Role entity) {
        if (entity == null) {
            return null;
        }
        return new RoleResponse(
                entity.getId(),
                entity.getAuthority(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive()
        );
    }

    public static RoleUserResponse toCustomResponse(Role entity) {
        return new RoleUserResponse(
                entity.getId(),
                entity.getAuthority(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive(),
                entity.getUsers().stream().map(r -> new UserCustomResponse(
                        r.getId(),
                        r.getFirstName(),
                        r.getLastName(),
                        r.getEmail(),
                        r.isActive()
                )).collect(Collectors.toSet())
        );
    }

    public static Role toRequest(RoleRequest request) {
        if (request.authority() == null) {
            return null;
        }
        Role entity = new Role();
        entity.setAuthority(request.authority());
        return entity;
    }

    public static Role toCreateRequest(RoleCreateRequest request) {
        Role entity = new Role();
        entity.setAuthority(request.authority());
        return entity;
    }


    public static Role toUpdateRequest(
            Long id,
            RoleUpdateRequest request,
            RoleRepository roleRepository
    ) {
        Role entity = roleRepository.getReferenceById(id);
        entity.setAuthority(request.authority());
        return entity;
    }

}
