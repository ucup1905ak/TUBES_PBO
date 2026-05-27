/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author farel
 */
import expection.QueryTypeMismatchException;
import java.util.List;
import utility.Query;

public interface IDatabaseConnection {

    public boolean isConnected();

    public <T> List<T> executeQuery(Query sql, IRowMapper<T> mapper) throws QueryTypeMismatchException;

    public <T> int executeUpdate(Query sql) throws QueryTypeMismatchException;
}
