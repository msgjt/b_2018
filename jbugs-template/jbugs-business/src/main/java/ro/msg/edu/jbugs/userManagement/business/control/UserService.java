package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.UserStatus;

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

    public List<UserDto> getAllUsers();

    public TokenDto login(String username, String password) throws BusinessException;

    void setUserStatus(Long id, UserStatus userStatus) throws BusinessException;
}
