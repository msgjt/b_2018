package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/*
    Api for role persistance
 */
public interface RoleDAO {

    Optional<Role> updateRole(@NotNull Role role);

    HashSet<Role> getRolesByType(@NotNull HashSet<RoleType> types);

    void addUser(@NotNull User user, @NotNull HashSet<Role> roles);

    List<Role> getAllRoles();

    List<Role> getUserRolesById(@NotNull Long id);

}
