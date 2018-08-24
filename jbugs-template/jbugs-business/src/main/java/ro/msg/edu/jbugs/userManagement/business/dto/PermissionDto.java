package ro.msg.edu.jbugs.userManagement.business.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "roles")
@ToString(callSuper = true, exclude = "roles")
@Builder
public class PermissionDto extends BaseDto{
    private String permissionType;
    private Set<RoleDto> roles;
}
