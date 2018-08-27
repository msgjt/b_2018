package ro.msg.edu.jbugs.userManagement.business.boundary;

import ro.msg.edu.jbugs.userManagement.business.control.PermissionService;
import ro.msg.edu.jbugs.userManagement.business.control.RoleService;
import ro.msg.edu.jbugs.userManagement.business.control.UserService;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

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
    public RoleDto updateRole(RoleDto roleDto) throws BusinessException {
        return roleService.updateRole(roleDto);
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
    public List<PermissionDto> getRolePermissionsById(Long id) {
        return permissionService.getRolePermissionsById(id);
    }

    @Override
    public TokenDto login(String username, String password) throws BusinessException {
        return userService.login(username, password);
    }

    @Override
    public Boolean setUserStatus(Long id, UserStatus userStatus) throws BusinessException {
        return userService.setUserStatus(id, userStatus);
    }

    @Override
    public List<BugDto> getBugsForUser(String username) throws BusinessException {
        return userService.getBugsForUser(username);
    }

    @Override
    public Boolean hasOpenBugsForUsername(String username) {
        return userService.hasOpenBugsForUsername(username);
    }
}
