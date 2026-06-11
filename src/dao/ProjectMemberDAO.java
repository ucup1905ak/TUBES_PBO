/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import java.util.List;
import javax.management.relation.Role;
import model.Project;
import model.User;
import model.enums.UserRole;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class ProjectMemberDAO {

    private final DatabaseConnection db = new DatabaseConnection();

    public int add(Integer projectId, Integer userId, UserRole roles) throws DatabaseException {
        Query sql = new Query();
        //Kasih IF ELSE YA 
        if (roles == UserRole.TEAM_MEMBER) {

        } else if (roles == UserRole.PROJECT_OWNER) {

        }
        sql.insertInto("project_member").values();
        //isi
        return db.executeUpdate(sql);
    }

    public int remove(Integer projectId, Integer userId) throws DatabaseException {
        Query sql = new Query();
        //isi
        return db.executeUpdate(sql);
    }

    public List<User> getUserByProject(Integer projectId) throws DatabaseException {
        Query sql = new Query();
        //isi
        List<User> list = db.executeQuery(sql, /* INI ISI DENGAN USER MAPPER*/);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public List<Project> getProjectByUser(Integer userId) throws DatabaseException {
        Query sql = new Query();
        //isi
        List<list> list = db.executeQuery(sql, /* INI ISI DENGAN PROJECT MAPPER*/);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public UserRole getRole(Integer projectId, Integer userId) throws DatabaseException {
        Query sql = new Query();
        
        //ISI
        
        return UserRole.PROJECT_OWNER;
    }

    public int updateRole(Integer projectId, Integer userId, Role role) throws DatabaseException {
        //isi
        return 1;
    }

}
