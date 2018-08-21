package ro.msg.edu.jbugs.userManagement.client.exception;

public class ClientException extends Exception {
    private ClientExceptionCode ClientExceptionCode;

    public ClientException(ClientExceptionCode ClientExceptionCode) {
        super(ClientExceptionCode.getMessage());
        this.ClientExceptionCode = ClientExceptionCode;
    }

    public ClientException(ClientExceptionCode code, String message) {
        super(message);
        ClientExceptionCode = code;
    }

    public ClientException(ClientExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        ClientExceptionCode = code;
    }

    public ClientException(ClientExceptionCode code, Throwable cause) {
        super(cause);
        ClientExceptionCode = code;
    }
}
