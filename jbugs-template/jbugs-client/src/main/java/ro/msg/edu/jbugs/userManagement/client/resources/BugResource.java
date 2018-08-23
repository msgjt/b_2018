package ro.msg.edu.jbugs.userManagement.client.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.boundary.UserManagementBoundary;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/bugs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BugResource {
    private static final Logger log = LogManager.getLogger(RoleResource.class);

    @EJB
    private ro.msg.edu.jbugs.userManagement.business.control.BugService bugService;

    @Path("/add")
    @POST
    public String addBug(BugDto bugDto) throws BusinessException {
        bugService.createBug(bugDto);
        return "test";
    }

    @Path("query")
    @GET
    public List<BugDto> getBugs(
            @DefaultValue("") @QueryParam("title") String title,
            @DefaultValue("") @QueryParam("version") String version,
            @DefaultValue("") @QueryParam("status") String status

    ) throws BusinessException {
        log.info("getBugs: --entered {}", title, version, status);

        SearchCriteria criteria = new SearchCriteria();
        criteria.setTitle(title);
        criteria.setVersion(version);
        criteria.setStatus(version);

        List<BugDto> bugs = new ArrayList<>();
        bugs.add(new BugDto("First bug created",
                "extreamly long description",
                "1",
                "2",
                new Date(),
                SeverityType.CRITICAL,
                BugStatusType.CLOSED,
                new User(),
                new User()
                ));
        bugs.add(new BugDto("First bug created",
                "extreamly long description",
                "1",
                "2",
                new Date(),
                SeverityType.CRITICAL,
                BugStatusType.CLOSED,
                new User(),
                new User()
        ));
        log.info("getUsers: result={}", bugs);
        return bugs;
    }
}
