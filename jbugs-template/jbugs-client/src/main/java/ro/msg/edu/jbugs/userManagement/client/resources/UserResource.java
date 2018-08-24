package ro.msg.edu.jbugs.userManagement.client.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.boundary.UserManagementBoundary;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDto;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.client.exception.ClientException;
import ro.msg.edu.jbugs.userManagement.client.exception.ClientExceptionCode;
import ro.msg.edu.jbugs.userManagement.client.filters.AuthorizationSecured;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.PermissionType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private static final Logger log = LogManager.getLogger(UserResource.class);

    @EJB
    private UserManagementBoundary userManagementBoundary;

    @EJB
    private SessionBean sessionBean;

//    @AuthorizationSecured({PermissionType.PERMISSION_MANAGEMENT, PermissionType.USER_MANAGEMENT})
    @GET
    public List<UserDto> getUsers() throws BusinessException {
        log.info("getUsers: --entered");
        List<UserDto> allUsers = userManagementBoundary.getAllUsers();
        log.info("getUsers: result={}", allUsers);
        return allUsers;
    }

    @Path("/login")
    @POST
    public Response login(UserDto userDto) throws BusinessException, ClientException {
        log.info("login rest: userDto={}}", userDto);
        if(sessionBean.getLoggedInUsers().contains(userDto.getUsername())){
            throw new ClientException(ClientExceptionCode.ALREADY_LOGGED_IN);
        }
        TokenDto tokenDto = userManagementBoundary.login(userDto.getUsername(), userDto.getPassword());
        sessionBean.getLoggedInUsers().add(userDto.getUsername());
        log.info("login rest: tokenDto={}", tokenDto);
        return Response.status(Response.Status.OK).entity(tokenDto).build();
    }

    @Path("/logout")
    @POST
    public Response logout(UserDto userDto) throws ClientException {
        log.info("logout: userDto={}", userDto);
        if(!sessionBean.getLoggedInUsers().contains(userDto.getUsername())){
            throw new ClientException(ClientExceptionCode.CAN_NOT_LOGOUT);
        }
        sessionBean.getLoggedInUsers().remove(userDto.getUsername());
        log.info("logout -- success");
        return Response.status(Response.Status.OK).entity(true).build();
    }

    @Path("/add")
    @AuthorizationSecured(PermissionType.USER_MANAGEMENT)
    @POST
    public Response addUser(UserDto userDto) throws BusinessException {
        log.info("addUser: userDto={}",userDto);
        UserDto result = userManagementBoundary.createUser(userDto);
        log.info("addUser: result={}",result);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @Path("/update")
    @PUT
    public Response updateUser(UserDto userDto) throws BusinessException {
        log.info("updateUser: userDto={}",userDto);
        UserDto result = userManagementBoundary.updateUser(userDto);
        log.info("updateUser: result={}",result);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @Path("/activate")
    @AuthorizationSecured(PermissionType.USER_MANAGEMENT)
    @POST
    public Response activateUser(UserDto userDto) throws BusinessException {
        log.info("activateUser: userDto={}",userDto);
        Boolean success = userManagementBoundary.setUserStatus(userDto.getId(), UserStatus.ACTIVE);
        log.info("activateUser -- success");
        return Response.status(Response.Status.OK).entity(success).build();
    }

    @Path("/deactivate")
    @AuthorizationSecured(PermissionType.USER_MANAGEMENT)
    @POST
    public Response deactivateUser(UserDto userDto) throws BusinessException, ClientException {
        log.info("deactivateUser: userDto={}",userDto);
        if(userManagementBoundary.hasOpenBugsForUsername(userDto.getUsername())) {
            throw new ClientException(ClientExceptionCode.HAS_UNFINISHED_BUGS);
        }
        Boolean success = userManagementBoundary.setUserStatus(userDto.getId(), UserStatus.DEACTIVATED);
        log.info("deactivateUser -- success");
        return Response.status(Response.Status.OK).entity(success).build();
    }

    @Path("/openBugs")
//    @AuthorizationSecured(PermissionType.USER_MANAGEMENT)
    @POST
    public Response hasOpenBugs(UserDto userDto) {
        log.info("hasOpenBugs: userDto={}",userDto);
        Boolean hasBugs = userManagementBoundary.hasOpenBugsForUsername(userDto.getUsername());
        log.info("hasOpenBugs -- success");
        return Response.status(Response.Status.OK).entity(hasBugs).build();
    }
}
