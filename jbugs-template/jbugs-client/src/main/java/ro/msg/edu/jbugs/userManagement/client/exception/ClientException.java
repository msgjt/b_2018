package ro.msg.edu.jbugs.userManagement.client.exception;

import lombok.Getter;

@Getter
public class ClientException extends Exception {
    private ClientExceptionCode clientExceptionCode;

    public ClientException(ClientExceptionCode clientExceptionCode) {
        super(clientExceptionCode.getMessage());
        this.clientExceptionCode = clientExceptionCode;
    }

    public ClientException(ClientExceptionCode code, String message) {
        super(message);
        clientExceptionCode = code;
    }

    public ClientException(ClientExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        clientExceptionCode = code;
    }

    public ClientException(ClientExceptionCode code, Throwable cause) {
        super(cause);
        clientExceptionCode = code;
    }
}
