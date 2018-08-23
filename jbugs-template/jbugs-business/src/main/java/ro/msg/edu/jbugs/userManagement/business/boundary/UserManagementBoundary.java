package ro.msg.edu.jbugs.userManagement.business.boundary;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;

import java.util.List;

public interface UserManagementBoundary {

    UserDto createUser(UserDto userDto) throws BusinessException;

    UserDto updateUser(UserDto userDto) throws BusinessException;

    RoleDto updateRole(RoleDto roleDto) throws BusinessException;

    public List<UserDto> getAllUsers();

    public List<RoleDto> getAllRoles();

    public List<PermissionDto> getAllPermissions();

    List<RoleDto> getUserRolesById(Long id);

    List<PermissionDto> getRolePermissionsById(Long id);

    public TokenDto login(String username, String password) throws BusinessException;
}
