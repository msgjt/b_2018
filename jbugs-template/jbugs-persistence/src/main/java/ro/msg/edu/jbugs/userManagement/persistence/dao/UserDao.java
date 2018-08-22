package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Api for user persistence
 */
public interface UserDao {

    Optional<User> createUser(@NotNull User user);

    Optional<User> updateUser(@NotNull User user);

    List<User> getAllUsers();

    Optional<User> getUserByUsername(@NotNull String username);

    Optional<User> getUserByUsernameWithRolesAndPermissions(@NotNull String username);

    Boolean setUserStatus(Long id, UserStatus userStatus);

    Boolean setUserStatusByUsername(String username, UserStatus userStatus);

    Optional<User> getUserWithEmail(@NotNull String email);

}
