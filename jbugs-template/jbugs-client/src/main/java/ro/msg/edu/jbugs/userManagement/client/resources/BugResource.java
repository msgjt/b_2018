package ro.msg.edu.jbugs.userManagement.client.resources;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
}
