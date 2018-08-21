package ro.msg.edu.jbugs.userManagement.persistence.entity;

import lombok.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.AttachmentType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "bug")
@ToString(callSuper = true, exclude = "bug")
@Builder
public class Attachment extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType;

    @Lob
    private byte[] content;

    @ManyToOne
    private Bug bug;

}
