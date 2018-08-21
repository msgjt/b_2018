package ro.msg.edu.jbugs.userManagement.business.dto;

import lombok.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class BugDto extends BaseDto{
    private String title;
    private String description;
    private String version;
    private String fixedInVersion;
    private Date dueDate;
    private SeverityType severityType;
    private BugStatusType bugStatusType;
    private User creator;
    private User assignee;


}
