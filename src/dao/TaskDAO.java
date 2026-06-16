package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import interfaces.IProjectItemDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import model.Task;
import model.User;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import utility.db.Query;
import utility.security.Log;

/**
 *
 * @author Silvanus
 */
public class TaskDAO implements IProjectItemDAO<Task>, IRowMapper<Task> {

    @Override
    public List<Task> fetchByProject(Project id) {
        if (id == null) {
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
                        "pi.updated_at as updated_at",
                        "t.priority as priority",
                        "t.status as status",
                        "t.start_date as start_date",
                        "t.due_date as due_date",
                        "t.completed_at as completed_at"
                )
                .from("tasks t")
                .join("project_items pi", "pi.id = t.project_item_id")
                .where("pi.project_id = ?", id.getId());
        try {
            List<Task> tasks = DB.executeQuery(sql, this::map);
            Log.create("TaskDAO.fetchByProject queried " + tasks.size() + " row(s).");
            return tasks;
        } catch (DatabaseException e) {
            Log.err("TaskDAO.fetchByProject failed: " + e.getMessage());
            throw new IllegalStateException("Failed to fetch tasks by project", e);
        }
    }

    @Override
    public List<User> fetchAsignee(Task id) {
        if (id == null) {
            return new ArrayList<>();
        }

        Query sql = new Query()
                .select("u.*")
                .from("users u")
                .join("project_item_assignees pia", "pia.user_id = u.id")
                .where("pia.project_item_id = ?", id.getId());

        try {
            List<User> assignees = DB.executeQuery(sql, rs -> {
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
                    throw new ResultSetParsingException(
                            "Failed to parse User from ResultSet",
                            e
                    );
                }
            });
            Log.create("TaskDAO.fetchAsignee queried " + assignees.size() + " row(s).");
            return assignees;
        } catch (DatabaseException e) {
            Log.err("TaskDAO.fetchAsignee failed: " + e.getMessage());
            throw new IllegalStateException("Failed to fetch task assignees", e);
        }
    }

    @Override
    public int add(Task entity) throws DatabaseException {
        try {
            Query sql1 = new Query();
            sql1.insertInto(
                "project_items",
                "title",
                "description",
                "project_id",
                "created_by"
            ).values(
                entity.getTitle(),
                entity.getDescription(),
                entity.getProject() != null ? entity.getProject().getId() : null,
                entity.getCreatedBy() != null ? entity.getCreatedBy().getId() : null
            );
            int generatedId = DB.executeInsert(sql1);
            if (generatedId == -1) {
                throw new DatabaseException("Failed to insert ProjectItem for Task");
            }
            entity.setId(generatedId);

            Query sql2 = new Query();
            sql2.insertInto(
                    "tasks",
                    "project_item_id",
                    "priority",
                    "status",
                    "start_date",
                    "due_date",
                    "completed_at"
            ).values(
                    entity.getId(),
                    entity.getPriority(),
                    entity.getStatus(),
                    toTimestamp(entity.getStartDate()),
                    toTimestamp(entity.getDueDate()),
                    toTimestamp(entity.getCompletedAt())
            );

            int rows = DB.executeUpdate(sql2);
            Log.create("TaskDAO.add updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("TaskDAO.add failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Task get(Integer id) throws DatabaseException {
        if (id == null) {
            return null;
        }

        try {
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
                            "pi.updated_at as updated_at",
                            "t.priority as priority",
                            "t.status as status",
                            "t.start_date as start_date",
                            "t.due_date as due_date",
                            "t.completed_at as completed_at"
                    )
                    .from("tasks t")
                    .join("project_items pi", "pi.id = t.project_item_id")
                    .where("pi.id = ?", id);

            List<Task> tasks = DB.executeQuery(sql, this::map);
            Log.create("TaskDAO.get queried " + tasks.size() + " row(s).");
            return tasks.isEmpty() ? null : tasks.get(0);
        } catch (DatabaseException e) {
            Log.err("TaskDAO.get failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Task> fetchAll() throws DatabaseException {
        try {
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
                    "pi.updated_at as updated_at",
                    "t.priority as priority",
                    "t.status as status",
                    "t.start_date as start_date",
                    "t.due_date as due_date",
                    "t.completed_at as completed_at"
                )
                .from("tasks t")
                .join("project_items pi", "pi.id = t.project_item_id");

            List<Task> tasks = DB.executeQuery(sql, this::map);
            Log.create("TaskDAO.fetchAll queried " + tasks.size() + " row(s).");
            return tasks;
        } catch (DatabaseException e) {
            Log.err("TaskDAO.fetchAll failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Task entity) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("tasks")
                    .set("priority", entity.getPriority())
                    .set("status", entity.getStatus())
                    .set("start_date", toTimestamp(entity.getStartDate()))
                    .set("due_date", toTimestamp(entity.getDueDate()))
                    .set("completed_at", toTimestamp(entity.getCompletedAt()))
                    .where("project_item_id = ?", entity.getId());

            int rows = DB.executeUpdate(sql);
            Log.create("TaskDAO.update updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("TaskDAO.update failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        try {
            Query sql = new Query()
                    .deleteFrom("tasks")
                    .where("project_item_id = ?", id);

            int rows = DB.executeUpdate(sql);
            Log.create("TaskDAO.delete updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("TaskDAO.delete failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Task map(ResultSet rs) throws DatabaseException {
        try {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setColor(rs.getString("color"));

            Project project = new Project();
            project.setId(rs.getInt("project_id"));
            task.setProject(project);

            task.setCreatedAt(rs.getTimestamp("created_at"));
            task.setUpdatedAt(rs.getTimestamp("updated_at"));

            int createdById = rs.getInt("created_by");
            if (!rs.wasNull()) {
                User createdBy = new User();
                createdBy.setId(createdById);
                task.setCreatedBy(createdBy);
            }

            int updatedById = rs.getInt("updated_by");
            if (!rs.wasNull()) {
                User updatedBy = new User();
                updatedBy.setId(updatedById);
                task.setUpdatedBy(updatedBy);
            }

            String priority = rs.getString("priority");
            if (priority != null) {
                task.setPriority(TaskPriority.valueOf(normalizeEnumName(priority)));
            }

            String status = rs.getString("status");
            if (status != null) {
                task.setStatus(TaskStatus.valueOf(normalizeEnumName(status)));
            }

            task.setStartDate(toDate(rs.getTimestamp("start_date")));
            task.setDueDate(toDate(rs.getTimestamp("due_date")));
            task.setCompletedAt(toDate(rs.getTimestamp("completed_at")));

            return task;
        } catch (SQLException e) {
            throw new ResultSetParsingException(
                    "Failed to parse Task from ResultSet",
                    e
            );
        }
    }

    private java.util.Date toDate(Timestamp timestamp) {
        return timestamp == null ? null : new java.util.Date(timestamp.getTime());
    }

    private String normalizeEnumName(String value) {
        return value.trim().toUpperCase().replace('-', '_').replace(' ', '_');
    }

    private Timestamp toTimestamp(java.util.Date date) {
        return date == null ? null : new Timestamp(date.getTime());
    }

    /*
        (5/6)
    
        Ini Turunan dari ProjectItemDAO
        semoga work amin
    
        - Widi
     */
}
