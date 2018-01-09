package by.tc.web.service.database.pool.exception;

public class DBPoolException extends Exception {
    private static final long serialVersionUID = 6529081979722801135L;

    public DBPoolException() {
        super();
    }

    public DBPoolException(String message) {
        super(message);
    }

    public DBPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBPoolException(Throwable cause) {
        super(cause);
    }

    protected DBPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
