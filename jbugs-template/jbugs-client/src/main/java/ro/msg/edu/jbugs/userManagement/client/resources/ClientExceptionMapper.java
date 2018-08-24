package ro.msg.edu.jbugs.userManagement.client.resources;

import ro.msg.edu.jbugs.userManagement.client.exception.ClientException;

import javax.annotation.Priority;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1002)
public class ClientExceptionMapper implements ExceptionMapper<ClientException> {

    @Override
    public Response toResponse(ClientException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(e.getClientExceptionCode().name())
                .build();
    }
}
