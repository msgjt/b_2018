package ro.msg.edu.jbugs.userManagement.business.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.converter.PermissionConverter;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;
import ro.msg.edu.jbugs.userManagement.persistence.dao.PermissionDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PermissionServiceImpl implements PermissionService {

    private static final Logger log = LogManager.getLogger(PermissionServiceImpl.class);

    @EJB
    private PermissionDAO permissionDAO;

    @Inject
    private PermissionConverter permissionConverter;


    @Override
    public List<PermissionDto> getAllPermissions() {
        log.info("getAllPermissions: - entered");
        List<PermissionDto> permissions = new ArrayList<>(permissionConverter.convertEntitiesToDtos(permissionDAO.getAllPermissions()));
        log.info("getAllPermissions: result={}", permissions);
        return permissions;
    }
}
