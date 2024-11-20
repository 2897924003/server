package ss.user.usecase.exception;

public class BadCredentialsException extends BusinessException{
    public BadCredentialsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BadCredentialsException(String msg) {
        super(msg);
    }
}
