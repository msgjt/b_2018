package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"creator", "assignee", "attachments"})
@ToString(exclude = {"creator", "assignee", "attachments"})
@Builder
public class Bug extends BaseEntity<Long> {

    private String title;
    private String description;
    private String version;
    private String fixedInVersion;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private SeverityType severityType;

    @Enumerated(EnumType.STRING)
    private BugStatusType statusType;

    @ManyToOne
    private User creator;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bug")
    private Set<Attachment> attachments = new HashSet<>();


}
