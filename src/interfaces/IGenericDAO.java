package interfaces;

import model.Project;
import java.sql.SQLException;
import java.util.List;
import service.DatabaseConnection;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public interface IGenericDAO<T, ID> {

    DatabaseConnection DB = new DatabaseConnection();
    // Create
    public int add(T entity)throws SQLException;

    // Read
    public T get(ID id)throws SQLException;

    public List<T> fetchAll()throws SQLException;

    // Update
    public int update(T entity)throws SQLException;

    // Delete
    public int delete(ID id)throws SQLException;
}
