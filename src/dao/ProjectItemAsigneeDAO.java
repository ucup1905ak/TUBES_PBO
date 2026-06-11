/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import java.sql.SQLException;
import java.util.List;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import model.*;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class ProjectItemAsigneeDAO {

    private DatabaseConnection db = new DatabaseConnection();

    public int assignUser(Integer projectItemId, Integer userId) throws DatabaseException {
        Query sql = new Query();
        sql.insertInto("project_item_assignees", "project_item_id", "user_id").values(projectItemId, userId);
        return db.executeUpdate(sql);
    }

    public int removeAssignee(Integer projectItemId, Integer userId) throws DatabaseException {

        Query sql = new Query();
        sql.deleteFrom("project_item_assignees")
                .where("project_item_id = ? AND user_id = ? ", projectItemId, userId);
        return db.executeUpdate(sql);
    }

    public List<User> getAssignees(Integer projectItemId) throws DatabaseException {
        Query sql = new Query();

        return db.executeQuery(sql, rs -> {
                    User u = null;
                    try {
                        u = new User(rs.getString("username"),
                                rs.getString("full_name"),
                                rs.getString("email"),
                                rs.getString("password_hash"));
                        u.setId(rs.getInt("id"));
                        u.setBio(rs.getString("bio"));
                        u.setProfilePicture(rs.getString("profile_picture"));
                        u.setCreatedAt(rs.getTimestamp("created_at"));
                        u.setUpdatedAt(rs.getTimestamp("updated_at"));
                    } catch (SQLException e) {
                        throw new ResultSetParsingException(
                                "Failed to parse User from ResultSet",
                                e
                        );

                    }
                    return u;
        });
    }

    public List<ProjectItem> getAssignedItems(Integer userId) throws DatabaseException{
//        Query sql = new Query();
//
//        return db.executeQuery(sql);
           throw new UnsupportedOperationException("ProjectItem Asignee : getAssigend Items");
    }

}
