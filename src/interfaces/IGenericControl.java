package interfaces;

import java.util.List;

/**
 *
 * @author farel
 */
public interface IGenericControl<T, ID> {

    public int add(T entity);

    public T get(ID id);

    public List<T> fetchAll();

    public int update(T entity);

    public int delete(ID id);

}
