package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

import java.util.List;

public interface UserService {

    /**
     * This method is used for persisting a new user to the database
     * it generates the username and does the validations.
     *
     * @param userDto
     * @return The newly created user as a userDto which have the generated id.
     */
    UserDto createUser(UserDto userDto) throws BusinessException;

    List<UserDto> getAllUsers();

    TokenDto login(String username, String password) throws BusinessException;

    Boolean setUserStatus(Long id, UserStatus userStatus) throws BusinessException;

    UserDto updateUser(UserDto userDto) throws BusinessException;

    List<BugDto> getBugsForUser(String username) throws BusinessException;

    Boolean hasOpenBugsForUsername(String username);
}
