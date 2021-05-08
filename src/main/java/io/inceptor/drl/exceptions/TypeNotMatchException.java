package io.inceptor.drl.exceptions;

public class TypeNotMatchException extends RuntimeException {
    static String msg = "type not match any enum values";

    public TypeNotMatchException() {
        super(msg);
    }

    public TypeNotMatchException(String message) {
        super(message);
    }

    public TypeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeNotMatchException(Throwable cause) {
        super(cause);
    }

    protected TypeNotMatchException(String message, Throwable cause,
                                     boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
