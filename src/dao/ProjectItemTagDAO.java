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
import utility.security.Log;
import utility.db.Query;
/**
 *
 * @author Silvanus
 */
public class ProjectItemTagDAO {
    private final DatabaseConnection db = new DatabaseConnection();

    public int assignTag(Integer projectItemId, Integer tagId) throws DatabaseException {
        try {
            Query sql = new Query()
                    .insertInto("project_item_tags", "project_item_id", "tag_id")
                    .values(projectItemId, tagId);

            int rows = db.executeUpdate(sql);
            Log.create("ProjectItemTagDAO.assignTag updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectItemTagDAO.assignTag failed: " + e.getMessage());
            throw e;
        }
    }

    public int removeTag(Integer projectItemId, Integer tagId) throws DatabaseException {
        try {
            Query sql = new Query()
                    .deleteFrom("project_item_tags")
                    .where("project_item_id = ? AND tag_id = ? ", projectItemId, tagId);

            int rows = db.executeUpdate(sql);
            Log.create("ProjectItemTagDAO.removeTag updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("ProjectItemTagDAO.removeTag failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Tag> getTags(Integer projectItemId) throws DatabaseException {
        if (projectItemId == null) {
            return new ArrayList<>();
        }

        Query sql = new Query()
                .select("t.*")
                .from("project_item_tags pit")
                .join("tags t", "t.id = pit.tag_id")
                .where("pit.project_item_id = ?", projectItemId);

        try {
            List<Tag> tags = db.executeQuery(sql, rs -> {
            try {
                Tag tag = new Tag();
                tag.setId(rs.getInt("id"));
                tag.setName(rs.getString("name"));
                tag.setColor(rs.getString("color"));
                tag.setCreatedAt(rs.getTimestamp("created_at"));
                return tag;
            } catch (SQLException e) {
                throw new ResultSetParsingException(
                        "Failed to parse Tag from ResultSet",
                        e
                );
            }
        });
            Log.create("ProjectItemTagDAO.getTags queried " + tags.size() + " row(s).");
            return tags;
        } catch (DatabaseException e) {
            Log.err("ProjectItemTagDAO.getTags failed: " + e.getMessage());
            throw e;
        }
    }

    public List<ProjectItem> getTaggedItems(Integer tagId) throws DatabaseException {
        if (tagId == null) {
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
                .from("project_item_tags pit")
                .join("project_items pi", "pi.id = pit.project_item_id")
                .where("pit.tag_id = ?", tagId);

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
            Log.create("ProjectItemTagDAO.getTaggedItems queried " + items.size() + " row(s).");
            return items;
        } catch (DatabaseException e) {
            Log.err("ProjectItemTagDAO.getTaggedItems failed: " + e.getMessage());
            throw e;
        }
    }
}
