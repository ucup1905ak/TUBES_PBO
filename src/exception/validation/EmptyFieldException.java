package exception.validation;

/**
 *
 * @author farel
 */

public class EmptyFieldException extends Exception {

    public EmptyFieldException() {
        super();
    }

    public EmptyFieldException(String message) {
        super(message);
    }

    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyFieldException(Throwable cause) {
        super(cause);
    }
}