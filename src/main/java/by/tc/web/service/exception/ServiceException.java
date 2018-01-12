package by.tc.web.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = -8759374565230207568L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
