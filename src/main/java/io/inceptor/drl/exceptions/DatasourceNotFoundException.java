package io.inceptor.drl.exceptions;

public class DatasourceNotFoundException extends RuntimeException {
    static String msg = "this declared class has no initialize instance";

    public DatasourceNotFoundException() {
        super(msg);
    }

    public DatasourceNotFoundException(String message) {
        super(message);
    }

    public DatasourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatasourceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DatasourceNotFoundException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
