package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface PermissionDAO {

    List<Permission> getAllPermissions();

    List<Permission> getRolePermissionsById(@NotNull Long id);
}
