package ro.msg.edu.jbugs.userManagement.client.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;



@Provider
public class ResponseServerFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        // Authorize (allow) all domains to consume the content
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

        if (requestContext.getMethod().equals("OPTIONS")) {
            String reqHead = requestContext.getHeaderString("Access-Control-Request-Headers");

            if(null != reqHead){
                responseContext.getHeaders().add("Access-Control-Allow-Headers", reqHead);

            }
            responseContext.setStatusInfo(Response.Status.ACCEPTED);
        }

    }
}
