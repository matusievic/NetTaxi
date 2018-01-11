package by.tc.web.dao.exception;

public class DAOException extends Exception {
    private static final long serialVersionUID = -4368330267035090283L;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
