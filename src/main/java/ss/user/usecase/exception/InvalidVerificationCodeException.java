package ss.user.usecase.exception;

public class InvalidVerificationCodeException extends BusinessException{
    public InvalidVerificationCodeException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public InvalidVerificationCodeException(String msg) {
        super(msg);
    }
}
