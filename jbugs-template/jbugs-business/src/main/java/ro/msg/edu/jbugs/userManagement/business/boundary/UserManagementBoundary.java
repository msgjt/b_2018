package ro.msg.edu.jbugs.userManagement.business.boundary;

import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;

import java.util.List;

public interface UserManagementBoundary {

    UserDto createUser(UserDto userDto) throws BusinessException;

    UserDto updateUser(UserDto userDto) throws BusinessException;

    public List<UserDto> getAllUsers();

    public List<RoleDto> getAllRoles();

    List<RoleDto> getUserRolesById(Long id);

    public TokenDto login(String username, String password) throws BusinessException;
}
