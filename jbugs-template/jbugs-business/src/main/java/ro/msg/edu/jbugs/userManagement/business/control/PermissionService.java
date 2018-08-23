package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;

import java.util.List;

public interface PermissionService {

    public List<PermissionDto> getAllPermissions();

     List<PermissionDto> getRolePermissionsById(Long id);

}
