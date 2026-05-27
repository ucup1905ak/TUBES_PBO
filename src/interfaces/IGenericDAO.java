/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entity.Project;
import java.util.List;

/**
 *
 * @author farel
 */
public interface IGenericDAO<T, ID> {

    // Create
    public boolean add(T entity);

    // Read
    public T get(ID id);

    public List<T> fetchAll();

    // Update
    public boolean update(T entity);

    // Delete
    public boolean delete(ID id);
}
