/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Project;
import model.User;
import model.enums.UserRole;
import service.DatabaseConnection;
import utility.Log;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class ProjectMemberDAO {

    private final DatabaseConnection db = new DatabaseConnection();

    public int add(Integer projectId, Integer userId, UserRole roles) throws DatabaseException {
        try {
            Query sql = new Query()
                .insertInto("project_members", "project_id", "user_id", "role")
                .values(
                    projectId,
                    userId,
                    roles != null ? roles : UserRole.TEAM_MEMBER
                );

            int rows = db.executeUpdate(sql);
            Log.create("ProjectMemberDAO.add updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectMemberDAO.add failed: " + e.getMessage());
            throw e;
        }
    }

    public int remove(Integer projectId, Integer userId) throws DatabaseException {
        try {
            Query sql = new Query()
                .deleteFrom("project_members")
                .where("project_id = ? AND user_id = ? ", projectId, userId);

            int rows = db.executeUpdate(sql);
            Log.create("ProjectMemberDAO.remove updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectMemberDAO.remove failed: " + e.getMessage());
            throw e;
        }
    }

    public List<User> getUserByProject(Integer projectId) throws DatabaseException {
        Query sql = new Query()
            .select("u.*")
            .from("project_members pm")
            .join("users u", "u.id = pm.user_id")
            .where("pm.project_id = ?", projectId);

        try {
            List<User> list = db.executeQuery(sql, rs -> mapUser(rs));
            Log.create("ProjectMemberDAO.getUserByProject queried " + list.size() + " row(s).");
            if (list.isEmpty()) {
                return null;
            }
            return list;
        } catch (DatabaseException e) {
            Log.err("ProjectMemberDAO.getUserByProject failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Project> getProjectByUser(Integer userId) throws DatabaseException {
        Query sql = new Query()
                .select("p.*")
                .from("project_members pm")
                .join("projects p", "p.id = pm.project_id")
                .where("pm.user_id = ?", userId);

        try {
            List<Project> list = db.executeQuery(sql, rs -> mapProject(rs));
            Log.create("ProjectMemberDAO.getProjectByUser queried " + list.size() + " row(s).");
            if (list.isEmpty()) {
                return null;
            }
            return list;
        } catch (DatabaseException e) {
            Log.err("ProjectMemberDAO.getProjectByUser failed: " + e.getMessage());
            throw e;
        }
    }

    public UserRole getRole(Integer projectId, Integer userId) throws DatabaseException {
        Query sql = new Query()
                .select("role")
                .from("project_members")
                .where("project_id = ? AND user_id = ?", projectId, userId);

        try {
            List<UserRole> list = db.executeQuery(sql, rs -> UserRole.valueOf(rs.getString("role")));
            Log.create("ProjectMemberDAO.getRole queried " + list.size() + " row(s).");
            return list.isEmpty() ? null : list.get(0);
        } catch (DatabaseException e) {
            Log.err("ProjectMemberDAO.getRole failed: " + e.getMessage());
            throw e;
        }
    }

    public int updateRole(Integer projectId, Integer userId, UserRole role) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("project_members")
                    .set("role", role)
                    .where("project_id = ? AND user_id = ?", projectId, userId);

            int rows = db.executeUpdate(sql);
            Log.create("ProjectMemberDAO.updateRole updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectMemberDAO.updateRole failed: " + e.getMessage());
            throw e;
        }
    }

    private User mapUser(ResultSet rs) throws DatabaseException {
        try {
            User user = new User(
                    rs.getString("username"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("password_hash")
            );
            user.setId(rs.getInt("id"));
            user.setBio(rs.getString("bio"));
            user.setProfilePicture(rs.getString("profile_picture"));
            user.setCreatedAt(rs.getTimestamp("created_at"));
            user.setUpdatedAt(rs.getTimestamp("updated_at"));
            return user;
        } catch (SQLException e) {
            Log.err("ProjectMemberDAO.mapUser failed: " + e.getMessage());
            throw new ResultSetParsingException("Failed to parse User from ResultSet", e);
        }
    }

    private Project mapProject(ResultSet rs) throws DatabaseException {
        try {
            Project project = new Project();
            project.setId(rs.getInt("id"));
            project.setName(rs.getString("name"));
            project.setDescription(rs.getString("description"));
            project.setColor(rs.getString("color"));
            project.setCreatedAt(rs.getTimestamp("created_at"));
            project.setUpdatedAt(rs.getTimestamp("updated_at"));
            return project;
        } catch (SQLException e) {
            Log.err("ProjectMemberDAO.mapProject failed: " + e.getMessage());
            throw new ResultSetParsingException("Failed to parse Project from ResultSet", e);
        }
    }

}
