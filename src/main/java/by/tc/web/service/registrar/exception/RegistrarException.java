package by.tc.web.service.registrar.exception;

public class RegistrarException extends Exception {
    private static final long serialVersionUID = -772910873943086639L;

    public RegistrarException() {
        super();
    }

    public RegistrarException(String message) {
        super(message);
    }

    public RegistrarException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrarException(Throwable cause) {
        super(cause);
    }

    protected RegistrarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
