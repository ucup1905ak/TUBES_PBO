package exception.authentication;
/**
 *
 * @author farel
 */

public class InvalidLoginCredentialException extends Exception {

    public InvalidLoginCredentialException() {
        super();
    }

    public InvalidLoginCredentialException(String message) {
        super(message);
    }

    public InvalidLoginCredentialException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLoginCredentialException(Throwable cause) {
        super(cause);
    }
}
