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
    USER_DEACTIVATED(1001,"User is deactivated"),
    CAN_NOT_ADD_USER(1002, "Can't create user"),
    CAN_NOT_UPDATE_USER(1003, "Can't update user"),
    CAN_NOT_GET_USER(1004, "Can't get user"),
    USER_VALIDATION_EXCEPTION(1005, "ValidationException"),
    EMAIL_EXISTS_ALREADY(1010, "Email already exists"),
    USERNAME_NOT_VALID(1006, "Wrong username"),
    PASSWORD_NOT_VALID(1007, "Wrong password"),
    TOO_MANY_ALIKE_USERNAMES(1008,"There are no more suffixes to be generated, consider increasing the range."),
    ROLES_NOT_VALID(1009, "Invalid roles"),
    CAN_NOT_UPDATE_ROLE(1010, "Can't update role"),
    INVALID_TOKEN(5000,"The token is invalid."),
    CAN_NOT_FIND_BUG(2001, "Bug not found"),
    CAN_NOT_CLOSE_BUG(2002, "Bug can not be closed from this status"),
    CAN_NOT_CLOSE_BUG_STATUS(2003, "Bug status can not be changed from this status");
    Integer id;
    String message;
}
