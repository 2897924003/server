package ss.user.usecase.exception;

import java.util.function.Supplier;

public abstract class BusinessException extends RuntimeException implements Supplier<RuntimeException> {

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public BusinessException(String msg) {
        super(msg);
    }

    @Override
    public RuntimeException get() {
        return this;
    }

}
