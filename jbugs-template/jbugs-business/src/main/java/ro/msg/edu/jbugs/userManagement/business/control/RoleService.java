package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;

import java.util.List;

public interface RoleService {

    public List<RoleDto> getAllRoles();

    List<RoleDto> getUserRolesById(Long id);
}
