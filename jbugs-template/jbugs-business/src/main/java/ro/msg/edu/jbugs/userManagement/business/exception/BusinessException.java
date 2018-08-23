package ro.msg.edu.jbugs.userManagement.business.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private BusinessExceptionCode businessExceptionCode;

    public BusinessException(BusinessExceptionCode businessExceptionCode) {
        super(businessExceptionCode.getMessage());
        this.businessExceptionCode = businessExceptionCode;
    }

    public BusinessException(BusinessExceptionCode code, String message) {
        super(message);
        businessExceptionCode = code;
    }

    public BusinessException(BusinessExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        businessExceptionCode = code;
    }

    public BusinessException(BusinessExceptionCode code, Throwable cause) {
        super(cause);
        businessExceptionCode = code;
    }
}
