package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@RequestScoped
public class RoleConverter extends AbstractConverterBaseEntityConverter<Role, RoleDto> {

    @Inject
    private PermissionConverter permissionConverter;

    @Override
    public Role convertDtoToEntity(RoleDto roleDto) {
        Role role = Role.builder()
                .type(RoleType.valueOf(roleDto.getRoleType()))
                .build();
        if(roleDto.getPermissions() != null && !roleDto.getPermissions().isEmpty()){
            Set<Permission> permissions =
                    new HashSet<>(permissionConverter.convertDtosToEntities(roleDto.getPermissions()));
            role.setPermissions(permissions);
        }
        if(roleDto.getId() != null)
            role.setId(roleDto.getId());
        return role;
    }

    @Override
    public RoleDto convertEntityToDto(Role role) {
        return null;
    }
}
