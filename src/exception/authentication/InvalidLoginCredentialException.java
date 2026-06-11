package exception.authentication;
/**
 *
 * @author farel
 */

public class InvalidLoginCredentialException extends AuthenticationException {

    public InvalidLoginCredentialException(String message) {
        super(message);
    }

    public InvalidLoginCredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}