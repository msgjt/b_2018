package ro.msg.edu.jbugs.userManagement.business.utils;

import lombok.Getter;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Startup
@Singleton
@Getter
public class UtilBean {
    private Integer NUM_OF_ALLOWED_TRIES = 4;

    private Map<String, Integer> loggingInTries;

    @PostConstruct
    private void init(){
        loggingInTries = new HashMap<>();
    }

    public Boolean addTry(String username){
        if(loggingInTries.containsKey(username)){
            Integer currentValue = loggingInTries.get(username);
            if(currentValue.equals(NUM_OF_ALLOWED_TRIES)){
                return true;
            }
            currentValue++;
            loggingInTries.put(username, currentValue);
        } else {
            loggingInTries.put(username, 0);
        }
        return false;
    }

    public void resetTries(String username){
            loggingInTries.remove(username);
    }
}
