package ro.msg.edu.jbugs.userManagement.client.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exception.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.business.utils.JwtManager;
import ro.msg.edu.jbugs.userManagement.persistence.entity.RoleType;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

@AuthorizationSecured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                throw new NotAuthorizedException("Authorization header must be provided");
            }

            String token = authorizationHeader.substring("Bearer".length()).trim();


            Jws<Claims> claims = JwtManager.getInstance().parseToken(token);
            if (claims != null) {
                String subject = claims.getBody().getSubject();
//                String role = (String) claims.getBody().get("loggedInAs");

//                Class<?> resourceClass = resourceInfo.getResourceClass();
//                AuthorizationSecured classAnnot = resourceClass.getAnnotation(FortressProtected.class);
//                if (classAnnot != null) {
//                    // do something with annotation
//                }
                Method resourceMethod = resourceInfo.getResourceMethod();
                AuthorizationSecured methodAnnotation = resourceMethod.getAnnotation(AuthorizationSecured.class);
                RoleType[] roles = methodAnnotation.value();

                int i = 0;
                boolean hasRole = false;
                while (i < roles.length && !hasRole) {
                    if (claims.getBody().get(roles[i].toString()) != null) {
                        hasRole = true;
                    }
                    i++;
                }
                if (!hasRole) {
                    throw new NotAuthorizedException("Not authorized");
                }
//                for (RoleType r : roles) {
//                    if (claims.getBody().get(r.toString()) == null) {
//                        throw new BusinessException(ExceptionCode.INVALID_TOKEN);
//                    }
//                }
//                if (!"user".equals(role)) {
//                    throw new BusinessException(ExceptionCode.INVALID_TOKEN);
//                }
//                requestContext.setProperty("loggedInAs", role);

            } else {
                throw new BusinessException(ExceptionCode.INVALID_TOKEN);
            }
        } catch (Exception ex) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());

        }

    }
}
