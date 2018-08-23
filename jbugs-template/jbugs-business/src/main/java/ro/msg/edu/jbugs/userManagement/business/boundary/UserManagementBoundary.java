package ro.msg.edu.jbugs.userManagement.business.boundary;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

import java.util.List;

public interface UserManagementBoundary {

    UserDto createUser(UserDto userDto) throws BusinessException;

    UserDto updateUser(UserDto userDto) throws BusinessException;

    List<UserDto> getAllUsers();

    List<RoleDto> getAllRoles();

    TokenDto login(String username, String password) throws BusinessException;

    Boolean setUserStatus(Long id, UserStatus userStatus) throws BusinessException;

    List<BugDto> getBugsForUser(String username) throws BusinessException;

    Boolean hasOpenBugsForUsername(String username);
}
