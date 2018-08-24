package ro.msg.edu.jbugs.userManagement.client.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.boundary.UserManagementBoundary;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {
    private static final Logger log = LogManager.getLogger(RoleResource.class);

    @EJB
    private UserManagementBoundary userManagementBoundary;

    @GET
    public List<RoleDto> getRoles() {
        log.info("getRoles: --entered");
        List<RoleDto> allRoles = userManagementBoundary.getAllRoles();
        log.info("getRoles: result={}", allRoles);
        return allRoles;
    }

    @GET
    @Path("/{id}")
    public List<RoleDto> getUserRolesById(@PathParam("id") Long id) {
        log.info("getUserRolesById: id={}", id);
        List<RoleDto> userRoles = userManagementBoundary.getUserRolesById(id);
        log.info("getRoles: result={}", userRoles);
        return userRoles;
    }

    @Path("/update")
    @PUT
    public Response updateRole(RoleDto roleDto) throws BusinessException{
        log.info("updateRole: roleDto={}", roleDto);
        RoleDto result = userManagementBoundary.updateRole(roleDto);
        log.info("updateRole: result={}", result);
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
