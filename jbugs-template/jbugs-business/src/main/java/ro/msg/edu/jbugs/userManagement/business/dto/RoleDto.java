package ro.msg.edu.jbugs.userManagement.business.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"permissions", "users"})
@ToString(callSuper = true, exclude = {"permissions", "users"})
@Builder
public class RoleDto extends BaseDto {
    private String roleType;
    private List<PermissionDto> permissions;
    private List<UserDto> users;
}
