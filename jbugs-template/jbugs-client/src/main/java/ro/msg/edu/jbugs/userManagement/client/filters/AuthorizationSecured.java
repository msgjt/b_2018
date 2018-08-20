package ro.msg.edu.jbugs.userManagement.client.filters;

import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.PermissionType;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NameBinding
@Retention(RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AuthorizationSecured {
    PermissionType[] value() default {};
}
