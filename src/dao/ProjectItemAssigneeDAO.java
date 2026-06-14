/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;
import service.DatabaseConnection;
import utility.Log;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class ProjectItemAssigneeDAO {

    private final DatabaseConnection db = new DatabaseConnection();

    public int assignUser(Integer projectItemId, Integer userId) throws DatabaseException {
        try {
            Query sql = new Query()
                    .insertInto("project_item_assignees", "project_item_id", "user_id")
                    .values(projectItemId, userId);
            int rows = db.executeUpdate(sql);
            Log.create("ProjectItemAsigneeDAO.assignUser updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectItemAsigneeDAO.assignUser failed: " + e.getMessage());
            throw e;
        }
    }

    public int removeAssignee(Integer projectItemId, Integer userId) throws DatabaseException {

        try {
            Query sql = new Query()
                .deleteFrom("project_item_assignees")
                .where("project_item_id = ? AND user_id = ? ", projectItemId, userId);
            int rows = db.executeUpdate(sql);
            Log.create("ProjectItemAsigneeDAO.removeAssignee updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectItemAsigneeDAO.removeAssignee failed: " + e.getMessage());
            throw e;
        }
    }

    public List<User> getAssignees(Integer projectItemId) throws DatabaseException {
        if (projectItemId == null) {
            return new ArrayList<>();
        }

        Query sql = new Query()
            .select("u.*")
            .from("project_item_assignees pia")
            .join("users u", "u.id = pia.user_id")
            .where("pia.project_item_id = ?", projectItemId);

        try {
            List<User> assignees = db.executeQuery(sql, rs -> {
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
            Log.create("ProjectItemAsigneeDAO.getAssignees queried " + assignees.size() + " row(s).");
            return assignees;
        } catch (DatabaseException e) {
            Log.err("ProjectItemAsigneeDAO.getAssignees failed: " + e.getMessage());
            throw e;
        }
    }

    public List<ProjectItem> getAssignedItems(Integer userId) throws DatabaseException{
        if (userId == null) {
            return new ArrayList<>();
        }

        Query sql = new Query()
                .select(
                        "pi.id as id",
                        "pi.title as title",
                        "pi.description as description",
                        "pi.color as color",
                        "pi.project_id as project_id",
                        "pi.created_by as created_by",
                        "pi.updated_by as updated_by",
                        "pi.created_at as created_at",
                        "pi.updated_at as updated_at"
                )
                .from("project_item_assignees pia")
                .join("project_items pi", "pi.id = pia.project_item_id")
                .where("pia.user_id = ?", userId);

        try {
            List<ProjectItem> items = db.executeQuery(sql, rs -> {
            try {
                ProjectItem item = new ProjectItem() {};
                item.setId(rs.getInt("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setColor(rs.getString("color"));

                Project project = new Project();
                project.setId(rs.getInt("project_id"));
                item.setProject(project);

                item.setCreatedAt(rs.getTimestamp("created_at"));
                item.setUpdatedAt(rs.getTimestamp("updated_at"));

                int createdById = rs.getInt("created_by");
                if (!rs.wasNull()) {
                    User createdBy = new User();
                    createdBy.setId(createdById);
                    item.setCreatedBy(createdBy);
                }

                int updatedById = rs.getInt("updated_by");
                if (!rs.wasNull()) {
                    User updatedBy = new User();
                    updatedBy.setId(updatedById);
                    item.setUpdatedBy(updatedBy);
                }

                return item;
            } catch (SQLException e) {
                throw new ResultSetParsingException(
                        "Failed to parse ProjectItem from ResultSet",
                        e
                );
            }
        });
            Log.create("ProjectItemAsigneeDAO.getAssignedItems queried " + items.size() + " row(s).");
            return items;
        } catch (DatabaseException e) {
            Log.err("ProjectItemAsigneeDAO.getAssignedItems failed: " + e.getMessage());
            throw e;
        }
    }

}
