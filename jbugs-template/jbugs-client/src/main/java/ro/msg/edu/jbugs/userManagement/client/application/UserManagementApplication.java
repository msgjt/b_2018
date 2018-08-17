package ro.msg.edu.jbugs.userManagement.client.application;

import ro.msg.edu.jbugs.userManagement.client.resources.UserResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api/")
public class UserManagementApplication extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(UserResource.class);
        return super.getClasses();
    }
}
