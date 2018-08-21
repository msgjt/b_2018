package ro.msg.edu.jbugs.userManagement.client.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ClientExceptionCode {
    ALREADY_LOGGED_IN(2000, "This user is already logged in"),
    CAN_NOT_LOGOUT(2001, "Error logging out");
    Integer id;
    String message;
}
