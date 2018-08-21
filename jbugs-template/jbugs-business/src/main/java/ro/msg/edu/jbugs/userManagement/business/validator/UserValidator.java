package ro.msg.edu.jbugs.userManagement.business.validator;

import com.sun.javaws.exceptions.InvalidArgumentException;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static void validateUser(User user) throws BusinessException {
        if(!userRequiredFieldsNotNull(user) || !validEmail(user.getEmail()) ||
                !validPhoneNumber(user.getMobileNumber())) {
            throw new BusinessException(BusinessExceptionCode.USER_VALIDATION_EXCEPTION);
        }
    }

    private static boolean userRequiredFieldsNotNull(User user){
        return user.getFirstName() != null &&
                user.getLastName() != null &&
                user.getEmail() != null &&
                user.getPassword() != null &&
                user.getMobileNumber() != null;
    }

    private static boolean validEmail(String email){
        return email.matches("^[A-Za-z0-9._%+-]+@msggroup.com$");
    }

    private static boolean validPhoneNumber(String phoneNumber){
       final Pattern VALID_GERMANY_PHONE_REGEX =
                Pattern.compile("[0-9]*\\/*(\\+49)*[ ]*(\\([0-9]+\\))*([ ]*(-)*[ ]*[0-9]+)*", Pattern.CASE_INSENSITIVE);
        final Pattern VALID_ROMANIA_PHONE_REGEX =
                Pattern.compile("(^\\+407|^07|^\\+402|^02)([0-9]{8})", Pattern.CASE_INSENSITIVE);
        Matcher matcherGermany = VALID_GERMANY_PHONE_REGEX.matcher(phoneNumber);
        Matcher matcherRomania = VALID_ROMANIA_PHONE_REGEX.matcher(phoneNumber);
        return matcherGermany.find() || matcherRomania.find();
    }

    public static HashSet<RoleType> validateRoles(String[] roles) {
        HashSet<RoleType> toReturn = new HashSet<>();
        if (roles.length == 0 || roles.length > 5)
            return toReturn;
        for (String role: roles) {
            try {
                if (!toReturn.add(RoleType.valueOf(role))) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                return new HashSet<>();
            }
        }
        return  toReturn;
    }
}
