package ro.msg.edu.jbugs.userManagement.client.resources;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessExceptionCode;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bugs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BugResource {
    private static final Logger log = LogManager.getLogger(BugResource.class);

    @EJB
    private ro.msg.edu.jbugs.userManagement.business.control.BugService bugService;

    @Path("/add")
    @POST
    public String addBug(BugDto bugDto) throws BusinessException {
        log.info("logInfo" + bugDto.getAssignee().getUsername());
        bugService.createBug(bugDto);
        return "bug added successfully";
    }

    @Path("/close")
    @POST
    public Response closeBug(Long bugId) throws BusinessException {
        log.info("close bug in ressource: bugId={}", bugId);
        if (bugService.closeBug(bugId)) {
            return Response.ok().build();
        }
        throw new BusinessException(BusinessExceptionCode.CAN_NOT_CLOSE_BUG);
    }

    @Path("/changestatus")
    @POST
    public Response changeBugStatus(BugDto bugDto) throws  BusinessException{
        log.info("change bug status in ressource: bugId={} newStatus={}", bugDto.getId(), bugDto.getBugStatusType());
        if (bugService.changeBugStatus(bugDto)) {
            log.info("bug status changed");
            return Response.ok().build();
        }
        log.info("bug status not changed");
        throw new BusinessException(BusinessExceptionCode.CAN_NOT_CLOSE_BUG_STATUS);
    }
}