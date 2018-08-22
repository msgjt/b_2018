package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;

/*
    Api for role persistance
 */
public interface RoleDAO {

    List<Role> getAllRoles();

    List<Role> getUserRolesById(@NotNull Long id);

}
