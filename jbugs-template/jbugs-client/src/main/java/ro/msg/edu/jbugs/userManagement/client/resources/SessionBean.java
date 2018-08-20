package ro.msg.edu.jbugs.userManagement.client.resources;

import lombok.Getter;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Startup
@Singleton
@Getter
public class SessionBean {
    private List<String> loggedInUsers;

    @PostConstruct
    private void init(){
        loggedInUsers = new ArrayList<>();
    }



}
