package io.inceptor.drl.exceptions;

public class DrlFileNoFoundException extends RuntimeException {
    static String msg = "this declared class has no initialize instance";

    public DrlFileNoFoundException() {
        super(msg);
    }

    public DrlFileNoFoundException(String message) {
        super(message);
    }

    public DrlFileNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrlFileNoFoundException(Throwable cause) {
        super(cause);
    }

    protected DrlFileNoFoundException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
