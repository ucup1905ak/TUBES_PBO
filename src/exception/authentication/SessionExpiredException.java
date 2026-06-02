package exception.authentication;

/**
 *
 * @author farel
 */

public class SessionExpiredException extends AuthenticationException {

    public SessionExpiredException(String message) {
        super(message);
    }

    public SessionExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}