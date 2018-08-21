package ro.msg.edu.jbugs.userManagement.business.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class TokenDto extends BaseDto {
    private String token;
    private String username;
}
