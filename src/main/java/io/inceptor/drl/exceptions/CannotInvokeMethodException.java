package io.inceptor.drl.exceptions;

public class CannotInvokeMethodException extends RuntimeException {
    static String msg = "can't invoke this method here";

    public CannotInvokeMethodException() {
        super(msg);
    }

    public CannotInvokeMethodException(String message) {
        super(message);
    }

    public CannotInvokeMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotInvokeMethodException(Throwable cause) {
        super(cause);
    }

    protected CannotInvokeMethodException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
