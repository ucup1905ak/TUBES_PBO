package interfaces;

import exception.database.DatabaseException;
import java.util.List;
import service.DatabaseConnection;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public interface IGenericDAO<T, ID> {

    DatabaseConnection DB = new DatabaseConnection();
    // Create
    public int add(T entity)throws DatabaseException;

    // Read
    public T get(ID id)throws DatabaseException;

    public List<T> fetchAll()throws DatabaseException;

    // Update
    public int update(T entity)throws DatabaseException;

    // Delete
    public int delete(ID id)throws DatabaseException;
}
