package ro.msg.edu.jbugs.userManagement.business.boundary;

import ro.msg.edu.jbugs.userManagement.business.control.PermissionService;
import ro.msg.edu.jbugs.userManagement.business.control.RoleService;
import ro.msg.edu.jbugs.userManagement.business.control.UserService;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserManagementBoundaryImpl implements UserManagementBoundary {

    @EJB
    private UserService userService;

    @EJB
    private RoleService roleService;

    @EJB
    private PermissionService permissionService;

    @Override
    public UserDto createUser(UserDto userDto) throws BusinessException {
        return userService.createUser(userDto);
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws BusinessException {
        return userService.updateUser(userDto);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Override
    public List<PermissionDto> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @Override
    public List<RoleDto> getUserRolesById(Long id) {
        return roleService.getUserRolesById(id);
    }

    @Override
    public TokenDto login(String username, String password) throws BusinessException {
        return userService.login(username, password);
    }
}
