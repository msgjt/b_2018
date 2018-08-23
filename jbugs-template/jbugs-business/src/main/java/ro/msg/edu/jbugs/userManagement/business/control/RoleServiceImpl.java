package ro.msg.edu.jbugs.userManagement.business.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.converter.RoleConverter;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.dao.RoleDAO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class RoleServiceImpl implements RoleService{
    private static final Logger log = LogManager.getLogger(RoleServiceImpl.class);

    @EJB
    private RoleDAO roleDAO;

    @Inject
    private RoleConverter roleConverter;

    @Override
    public List<RoleDto> getAllRoles() {
        log.info("getAllRoles: --- entered");
        List<RoleDto> roles = new ArrayList<>(roleConverter.convertEntitiesToDtos(roleDAO.getAllRoles()));
        log.info("getAllRoles: result={}", roles);
        return roles;
    }

    @Override
    public List<RoleDto> getUserRolesById(Long id) {
        log.info("getUserRolesById: id={}",id);
        List<RoleDto> roles = new ArrayList<>(roleConverter.convertEntitiesToDtos(roleDAO.getUserRolesById(id)));
        log.info("getUserRolesById: result={}", roles);
        return roles;
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto) throws BusinessException {
        log.trace("updateRole: roleDto={}", roleDto);
        Role role = roleConverter.convertDtoToEntity(roleDto);
        Optional<Role> optionalRole = roleDAO.updateRole(role);
        Role roleResult = optionalRole
                .orElseThrow(() -> new BusinessException(BusinessExceptionCode.CAN_NOT_UPDATE_ROLE));
        RoleDto result = roleConverter.convertEntityToDto(roleResult);
        log.trace("updateRole: result={}", result);
        return result;
    }
}
