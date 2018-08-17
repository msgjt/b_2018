package ro.msg.edu.jbugs.userManagement.business.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Provides exception codes and description
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ExceptionCode {
    INVALID_USER(999,"User does not exist"),
    USER_VALIDATION_EXCEPTION(1000, "ValidationException"),
    EMAIL_EXISTS_ALREADY(1001, "Email already exists"),
    USERNAME_NOT_VALID(1002, "Wrong username"),
    PASSWORD_NOT_VALID(1003, "Wrong password"),
    TOO_MANY_ALIKE_USERNAMES(1004,"There are no more suffixes to be generated, consider increasing the range."),
    INVALID_TOKEN(5000,"The token is invalid.");
    Integer id;
    String message;
}
