package ro.msg.edu.jbugs.userManagement.business.dto;

import lombok.*;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.AttachmentType;

import javax.persistence.EnumType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class AtachementDto extends BaseDto {
    private AttachmentType attachmentType;
    private byte[] content;
    private Bug bug;
}
