/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Project;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class ProjectDAO implements IGenericDAO<Project, Integer>, IRowMapper<Project>{
    
    private final DatabaseConnection db = new DatabaseConnection();
    
    /**
     * (28/5)
     * 
     * Semua method di sini konsepnya sama kayak UserDAO
     * Dengan meninggikan nama Yesus, semoga bekerja
     * - Widi
     * 
     */
    
    @Override
    public int add(Project entity) throws SQLException {
        Query sql = new Query();

        sql.insertInto("projects",
                "name",
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
        
        List<Project> listProject = db.executeQuery(sql, this::map);
        if (listProject.isEmpty()) {
            return null;
        }
        return listProject.get(0);
    }

    @Override
    public List<Project> fetchAll() throws SQLException {
        Query sql = new Query()
                .select("*")
                .from("projects");
        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(Project entity) throws SQLException {
        Query sql = new Query()
                    .update("projects")
                    .set("name", entity.getName())
                    .set("description", entity.getDescription())
                    .set("color", entity.getColor())
                    .set("updated_at", entity.getUpdatedAt())
                    .where("id = ?", entity.getId());
        
        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws SQLException {
        Query sql = new Query()
                .deleteFrom("projects")
                .where("id  = ?", id);

        return db.executeUpdate(sql);
    }
    
    public Project map(ResultSet rs) throws SQLException{
        Project p = new Project();

        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setDescription(rs.getString("description"));
        p.setColor(rs.getString("color"));
        p.setCreatedAt(rs.getTimestamp("created_at"));
        p.setUpdatedAt(rs.getTimestamp("updated_at"));
        
        return p;
    }
    
}
