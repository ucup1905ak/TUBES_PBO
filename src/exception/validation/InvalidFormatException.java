/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception.validation;

/**
 *
 * @author farel
 */

public class InvalidFormatException extends ValidationException {

    private final String fieldName;
    private final String expectedFormat;

    public InvalidFormatException(String message) {
        super(message);
        this.fieldName = null;
        this.expectedFormat = null;
    }

    public InvalidFormatException(
            String fieldName,
            String expectedFormat
    ) {
        super(fieldName + " has invalid format. Expected: " + expectedFormat);
        this.fieldName = fieldName;
        this.expectedFormat = expectedFormat;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getExpectedFormat() {
        return expectedFormat;
    }
}