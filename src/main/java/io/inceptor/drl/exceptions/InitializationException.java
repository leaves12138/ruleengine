package io.inceptor.drl.exceptions;

public class InitializationException extends RuntimeException{
    static String msg = "drl initialize failed";

    public InitializationException() {
        super(msg);
    }

    public InitializationException(String message) {
        super(message);
    }

    public InitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationException(Throwable cause) {
        super(cause);
    }

    protected InitializationException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
