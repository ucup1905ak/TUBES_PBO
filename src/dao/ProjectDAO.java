/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Project;
import interfaces.IGenericDAO;
import java.sql.SQLException;
import java.util.List;
import service.DatabaseConnection;
import utility.Query;

//TEST YEEHAW

/**
 *
 * @author Silvanus
 */
public class ProjectDAO implements IGenericDAO<Project, Integer>{
    
    private final DatabaseConnection db = new DatabaseConnection();
    
    @Override
    public int add(Project entity) throws SQLException {
        Query sql = new Query();

        sql.insertInto("name",
                "description",
                "color"
        )
                .values(
                        entity.getName(),
                        entity.getDescription(),
                        entity.getColor()
                );
        return db.executeUpdate(sql);
    }

    @Override
    public Project get(Integer id) throws SQLException {
        Query sql = new Query()
                .select("*")
                .from("projects")
                .where("id = ?", id);
        
        
    }

    @Override
    public List<Project> fetchAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int update(Project entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
}
