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
public class ProjectMemberDAO implements IRowMapper<ProjectMemberDAO>{
    private final DatabaseConnection db = new DatabaseConnection();
    
    public int add(ProjectMemberDAO member) throws SQLException{
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

    public List<ProjectMemberDAO> getByProject(Integer projectId) throws SQLException{
        Query sql = new Query();
        //isi
        List<ProjectMemberDAO> listProjectMember = db.executeQuery(sql, this::map);
        if (listProjectMember.isEmpty()) {
            return null;
        }
        return listProjectMember;
    }
    
    public List<ProjectMemberDAO> getByUser(Integer userId) throws SQLException{
        Query sql = new Query();
        //isi
        List<ProjectMemberDAO> listProjectMember = db.executeQuery(sql, this::map);
        if (listProjectMember.isEmpty()) {
            return null;
        }
        return listProjectMember;
    }

    public int updateRole(Integer projectId, Integer userId, Role role) throws SQLException{
        //isi
        return 1;
    }
    
    public ProjectMemberDAO map(ResultSet rs) throws SQLException{
        ProjectMemberDAO p = new ProjectMemberDAO();
        
        //isi
        return p;
    }
}
