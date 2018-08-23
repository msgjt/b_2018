package ro.msg.edu.jbugs.userManagement.client.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    private String title;
    private String version;
    private String fixedVersion;
    private String creator;
    private String assignee;
    private String status;
    private String severity;

}
