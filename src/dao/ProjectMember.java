/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.management.relation.Role;
import model.Attachment;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class ProjectMember implements IRowMapper<ProjectMember>{
    private final DatabaseConnection db = new DatabaseConnection();
    
    public int add(ProjectMember member) throws SQLException{
        Query sql = new Query();

        sql.insertInto("project_member").values();
        //isi
        return db.executeUpdate(sql);
    }
    
    public int remove(Integer projectId, Integer userId) throws SQLException{
        Query sql = new Query();
        //isi
        return db.executeUpdate(sql);
    }

    public List<ProjectMember> getByProject(Integer projectId) throws SQLException{
        Query sql = new Query();
        //isi
        List<ProjectMember> listProjectMember = db.executeQuery(sql, this::map);
        if (listProjectMember.isEmpty()) {
            return null;
        }
        return listProjectMember;
    }
    
    public List<ProjectMember> getByUser(Integer userId) throws SQLException{
        Query sql = new Query();
        //isi
        List<ProjectMember> listProjectMember = db.executeQuery(sql, this::map);
        if (listProjectMember.isEmpty()) {
            return null;
        }
        return listProjectMember;
    }

    public int updateRole(Integer projectId, Integer userId, Role role) throws SQLException{
        //isi
        return 1;
    }
    
    public ProjectMember map(ResultSet rs) throws SQLException{
        ProjectMember p = new ProjectMember();
        
        //isi
        return p;
    }
}
