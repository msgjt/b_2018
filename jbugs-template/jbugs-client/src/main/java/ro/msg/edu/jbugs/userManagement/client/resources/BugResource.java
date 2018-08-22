package ro.msg.edu.jbugs.userManagement.client.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.boundary.UserManagementBoundary;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bug")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BugResource {
    private static final Logger log = LogManager.getLogger(RoleResource.class);

    @EJB
    private ro.msg.edu.jbugs.userManagement.business.control.BugService bugService;

    @GET
    public String addBug() throws BusinessException {
        log.info("getRoles: --entered");
        BugDto bugDto = new BugDto();
//        bugDto.setCreator(user);
//        bugDto.setAssignee(user);
        bugDto.setId(2l);
        bugDto.setTitle("titlu1");
        bugDto.setDescription("descript");
        bugDto.setVersion("1.0");
        bugDto.setFixedInVersion("1.0");
        bugDto.setSeverityType(SeverityType.HIGH);
        bugDto.setBugStatusType(BugStatusType.OPEN);
        bugService.createBug(bugDto);
        return "test";
    }
}
