/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception.database;

/**
 *
 * @author farel
 */
public class ResultSetParsingException extends DatabaseException{
  
    private final String columnName;

    public ResultSetParsingException(
            String columnName,
            Throwable cause) {
        super("Failed to parse column: " + columnName, cause);
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }  
}
