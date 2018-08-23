package ro.msg.edu.jbugs.userManagement.client.resources;


import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;


@Path("/bugs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BugResource {

    @EJB
    private ro.msg.edu.jbugs.userManagement.business.control.BugService bugService;

    @Path("/add")
    @POST
    public String addBug(BugDto bugDto) throws BusinessException {
        bugService.createBug(bugDto);
        return "bug added successfully";
    }
}
