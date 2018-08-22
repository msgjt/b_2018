package ro.msg.edu.jbugs.userManagement.client.resources;

import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;

import javax.annotation.Priority;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1001)
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getBusinessExceptionCode().name())
                .build();
    }
}
