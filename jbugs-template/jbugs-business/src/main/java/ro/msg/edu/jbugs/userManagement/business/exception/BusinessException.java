package ro.msg.edu.jbugs.userManagement.business.exception;

public class BusinessException extends Exception {
    private ExceptionCode exceptionCode;

    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public BusinessException(ExceptionCode code, String message) {
        super(message);
        exceptionCode = code;
    }

    public BusinessException(ExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        exceptionCode = code;
    }

    public BusinessException(ExceptionCode code, Throwable cause) {
        super(cause);
        exceptionCode = code;
    }
}
