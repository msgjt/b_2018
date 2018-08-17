package ro.msg.edu.jbugs.userManagement.business.validator;

import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exception.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

public class UserValidator {

    public static void validateUser(User user) throws BusinessException {
        if(!userRequiredFieldsNotNull(user) || !validEmail(user.getEmail()) || !validPhoneNumber(user.getMobileNumber())){
            throw new BusinessException(ExceptionCode.USER_VALIDATION_EXCEPTION);
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
//        return phoneNumber.matches("")
        return true;
    }
}
