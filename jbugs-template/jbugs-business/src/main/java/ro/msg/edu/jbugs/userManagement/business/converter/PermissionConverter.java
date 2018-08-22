package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PermissionConverter extends AbstractConverterBaseEntityConverter<Permission, PermissionDto> {

    @Override
    public Permission convertDtoToEntity(PermissionDto permissionDto) {
        return null;
    }

    @Override
    public PermissionDto convertEntityToDto(Permission permission) {
        return null;
    }
}
