package io.inceptor.drl.exceptions;

public class ParseDrlRuntimeException extends RuntimeException {
    static String msg = "drl parsed failed";

    public ParseDrlRuntimeException() {
        super(msg);
    }

    public ParseDrlRuntimeException(String message) {
        super(message);
    }

    public ParseDrlRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseDrlRuntimeException(Throwable cause) {
        super(cause);
    }

    protected ParseDrlRuntimeException(String message, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
