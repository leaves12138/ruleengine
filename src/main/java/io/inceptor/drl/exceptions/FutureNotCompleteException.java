package io.inceptor.drl.exceptions;

public class FutureNotCompleteException extends RuntimeException {
    static String msg = "future not complete!";

    public FutureNotCompleteException() {
        super(msg);
    }

    public FutureNotCompleteException(String message) {
        super(message);
    }

    public FutureNotCompleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public FutureNotCompleteException(Throwable cause) {
        super(cause);
    }

    public FutureNotCompleteException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
