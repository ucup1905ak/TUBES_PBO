/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception.database;

/**
 *
 * @author farel
 */
public class QueryExecutionException extends DatabaseException {

    private String query;

    public QueryExecutionException(String message) {
        super(message);
    }

    public QueryExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryExecutionException(String query, Throwable cause) {
        super("Failed to execute query: " + query, cause);
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}