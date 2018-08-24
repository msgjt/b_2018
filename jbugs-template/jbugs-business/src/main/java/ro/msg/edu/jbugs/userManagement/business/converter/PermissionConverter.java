package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.PermissionType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@RequestScoped
public class PermissionConverter extends AbstractConverterBaseEntityConverter<Permission, PermissionDto> {

    @Inject
    private RoleConverter roleConverter;

    @Override
    public Permission convertDtoToEntity(PermissionDto permissionDto) {
        Permission permission = Permission.builder()
                .type(PermissionType.valueOf(permissionDto.getPermissionType()))
                .build();
        if(permissionDto.getRoles() != null && !permissionDto.getRoles().isEmpty()){
            Set<Role> roles =
                    new HashSet<>(roleConverter.convertDtosToEntities(permissionDto.getRoles()));
            permission.setRoles(roles);
        }
        if(permissionDto.getId() != null){
            permission.setId(permissionDto.getId());
        }
        return permission;
    }

    @Override
    public PermissionDto convertEntityToDto(Permission permission) {
        PermissionDto permissionDto = PermissionDto.builder()
                .permissionType(permission.getType().name())
                .build();
        permissionDto.setId(permission.getId());
        return permissionDto;
    }
}
