package ro.msg.edu.jbugs.userManagement.client.resources;

import lombok.Getter;
import lombok.Setter;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;

@Getter
@Setter
public class SearchCriteria {
    /**
     * Title to search for
     */
    private String title;
    /**
     * Version to search for
     */
    private String version;

    /**
     * FixedVersion to search for
     */
    private String fixedVersion;

    /**
     * Creators username to search for
     */
    private String creator;

    /**
     * assignees username to search for
     */
    private String assignee;

    /**
     * status of the bug to search for
     */
    private BugStatusType status;

    /**
     * severity of bug to search for
     */
    private SeverityType severity;

}
