package io.inceptor.drl.exceptions;

public class NoInstanceException extends RuntimeException {

    static String msg = "this declared class has no initialize instance";

    public NoInstanceException() {
        super(msg);
    }

    public NoInstanceException(String message) {
        super(message);
    }

    public NoInstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoInstanceException(Throwable cause) {
        super(cause);
    }

    protected NoInstanceException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
