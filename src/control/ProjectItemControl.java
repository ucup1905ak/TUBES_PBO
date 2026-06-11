/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ProjectItemDAO;
import dao.TaskDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.util.List;
import model.ProjectItem;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public abstract class ProjectItemControl implements IGenericControl<ProjectItem, Integer>{
   
    
    @Override
    public int add(ProjectItem entity) throws DatabaseException {
        if()
    }

    @Override
    public ProjectItem get(Integer id) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ProjectItem> fetchAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(ProjectItem entity) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
