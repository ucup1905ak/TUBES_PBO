package interfaces;

import exception.database.DatabaseException;
import java.util.List;

/**
 *
 * @author farel
 */
public interface IGenericControl<T, ID> {

    public int add(T entity) throws DatabaseException;

    public T get(ID id) throws DatabaseException;

    public List<T> fetchAll() throws DatabaseException;

    public int update(T entity)throws DatabaseException ;

    public int delete(ID id) throws DatabaseException;

}
