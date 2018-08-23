package ro.msg.edu.jbugs.userManagement.client.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.boundary.UserManagementBoundary;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/permissions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PermissionResource {
    private static final Logger log = LogManager.getLogger(PermissionResource.class);

    @EJB
    private UserManagementBoundary userManagementBoundary;

    @GET
    public List<PermissionDto> getPermissions() throws BusinessException{
        log.info("getPermissions: --entered");
        List<PermissionDto> allPermissions = userManagementBoundary.getAllPermissions();
        log.info("getPermissions: result={}", allPermissions);
        return allPermissions;
    }

    @GET
    @Path("/{id}")
    public List<PermissionDto> getRolePermissionsById(@PathParam("id") Long id) {
        log.info("getRolePermissionsById: id={}", id);
        List<PermissionDto> rolePermissions = userManagementBoundary.getRolePermissionsById(id);
        log.info("getPermissions: result={}", rolePermissions);
        return rolePermissions;
    }
}
