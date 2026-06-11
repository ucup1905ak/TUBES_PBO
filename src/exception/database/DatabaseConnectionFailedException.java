/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception.database;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public class DatabaseConnectionFailedException extends DatabaseException {

    public DatabaseConnectionFailedException(String message) {
        super(message);
    }

    public DatabaseConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}