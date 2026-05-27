/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entity.Project;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author farel
 */
public interface IGenericDAO<T, ID> {

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
