package io.inceptor.drl.exceptions;

public class ClassNotMatchException extends RuntimeException {
    static String msg = "this object creator doesn't match the object";

    public ClassNotMatchException() {
        super(msg);
    }

    public ClassNotMatchException(String message) {
        super(message);
    }

    public ClassNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassNotMatchException(Throwable cause) {
        super(cause);
    }

    protected ClassNotMatchException(String message, Throwable cause,
                                     boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
