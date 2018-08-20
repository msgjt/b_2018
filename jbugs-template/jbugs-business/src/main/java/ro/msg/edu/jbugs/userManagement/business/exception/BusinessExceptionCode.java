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
public enum BusinessExceptionCode {
    INVALID_USER(1000,"User does not exist"),
    USER_VALIDATION_EXCEPTION(1005, "ValidationException"),
    EMAIL_EXISTS_ALREADY(1010, "Email already exists"),
    USERNAME_NOT_VALID(1006, "Wrong username"),
    PASSWORD_NOT_VALID(1007, "Wrong password"),
    TOO_MANY_ALIKE_USERNAMES(1008,"There are no more suffixes to be generated, consider increasing the range."),
    INVALID_TOKEN(5000,"The token is invalid.");
    Integer id;
    String message;
}
