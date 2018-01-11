package by.tc.web.service.encryptor.exception;

public final class EncryptorException extends Exception {
    private static final long serialVersionUID = -5315736234458376564L;

    public EncryptorException() {
        super();
    }

    public EncryptorException(String message) {
        super(message);
    }

    public EncryptorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncryptorException(Throwable cause) {
        super(cause);
    }

    protected EncryptorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
