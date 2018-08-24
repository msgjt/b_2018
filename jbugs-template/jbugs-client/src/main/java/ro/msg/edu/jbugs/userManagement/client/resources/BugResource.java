package ro.msg.edu.jbugs.userManagement.client.resources;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.boundary.UserManagementBoundary;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessExceptionCode;
import ro.msg.edu.jbugs.userManagement.client.exception.ClientExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.core.Response;
import java.util.List;

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
        return "test";
    }

    @Path("query")
    @GET
    public List<BugDto> getBugs(
            @DefaultValue("") @QueryParam("title") String title,
            @DefaultValue("") @QueryParam("version") String version,
            @DefaultValue("") @QueryParam("fixedVerion") String fixedVersion,
            @DefaultValue("") @QueryParam("dueDate") String dueDate,
            @DefaultValue("") @QueryParam("severity") String severity,
            @DefaultValue("") @QueryParam("creator") String creator,
            @DefaultValue("") @QueryParam("assignee") String assignee,
            @DefaultValue("") @QueryParam("status") String status


    ) throws BusinessException {
        log.info("getBugs: --entered {}", title, version, status);

        SearchCriteria criteria = new SearchCriteria();
        criteria.setTitle(title);
        criteria.setVersion(version);
        criteria.setFixedVersion(fixedVersion);
        criteria.setSeverity(SeverityType.valueOf(severity));
        criteria.setCreator(creator);
        criteria.setAssignee(assignee);
        criteria.setStatus(BugStatusType.valueOf(status));
        //bugService
        log.info("getUsers: result");
        return new ArrayList<>();
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
    public Response changeBugStatus(BugDto bugDto) throws BusinessException {
        log.info("change bug status in ressource: bugId={} newStatus={}", bugDto.getId(), bugDto.getBugStatusType());
        if (bugService.changeBugStatus(bugDto)) {
            log.info("bug status changed");
            return Response.ok().build();
        }
        log.info("bug status not changed");
        throw new BusinessException(BusinessExceptionCode.CAN_NOT_CLOSE_BUG_STATUS);
    }
}
