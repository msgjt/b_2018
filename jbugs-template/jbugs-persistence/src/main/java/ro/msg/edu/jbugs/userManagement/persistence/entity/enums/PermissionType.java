package ro.msg.edu.jbugs.userManagement.persistence.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum PermissionType {
    PERMISSION_MANAGEMENT(5),
    USER_MANAGEMENT(4),
    BUG_MANAGEMENT(3),
    BUG_CLOSE(2),
    BUG_EXPORT_PDF(1);

    private int value;
}
