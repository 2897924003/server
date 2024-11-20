package ss.user.usecase.exception;



public class UserNotFoundException extends BusinessException{
    public UserNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }



}
