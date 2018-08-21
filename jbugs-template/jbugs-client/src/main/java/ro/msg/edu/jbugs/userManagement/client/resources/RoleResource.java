package ro.msg.edu.jbugs.userManagement.client.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.boundary.UserManagementBoundary;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {
    private static final Logger log = LogManager.getLogger(RoleResource.class);

    @EJB
    private UserManagementBoundary userManagementBoundary;

    @GET
    public List<RoleDto> getRoles() throws BusinessException {
        log.info("getRoles: --entered");
        List<RoleDto> allRoles = userManagementBoundary.getAllRoles();
        log.info("getRoles: result={}", allRoles);
        return allRoles;
    }
}
