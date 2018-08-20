package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    @ManyToOne
    private User user;
}
