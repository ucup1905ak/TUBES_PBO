package exception.validation;

/**
 *
 * @author farel
 */
public class EmptyFieldException extends ValidationException {

    private final String fieldName;

//    public EmptyFieldException(String message) {
//        super(message);
//        this.fieldName = null;
//    }

    public EmptyFieldException(String fieldName) {
        super(fieldName + " cannot be empty.");
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}