package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.NotificationType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.PermissionType;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "user")
@ToString(exclude = "user")
@Builder
public class Notification extends BaseEntity<Long> {

    private Long affectedUserId;
    private Long affectedBugId;

    @Temporal(TemporalType.DATE)
    private Date createdOn;

    private Boolean seen;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToOne
    private User user;
}
