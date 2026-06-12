package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import model.enums.TaskPriority;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Task;
import model.User;
import model.Project;
import model.enums.TaskStatus;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class TaskDAO extends ProjectItemDAO implements IGenericDAO<Task, Integer>, IRowMapper<Task>  {


    /*
        (5/6)
    
        Ini Turunan dari ProjectItemDAO
        semoga work amin
    
        - Widi
     */
    
    @Override
    public int add(Task entity) throws DatabaseException {
        int projectItemId = addProjectItem(entity);

        Query sql = new Query();

        sql.insertInto(
                "tasks",
                "project_item_id",
                "priority",
                "status",
                "start_date",
                "due_date",
                "completed_at"
        ).values(
                projectItemId,
                entity.getPriority(),
                entity.getStatus(),
                entity.getStartDate(),
                entity.getDueDate(),
                entity.getCompletedAt()
        );

        db.executeUpdate(sql);

        return projectItemId;
    }

    @Override
    public Task get(Integer id) throws DatabaseException {

        Query sql = new Query()
                .select("*")
                .from("tasks t")
                .join(
                        "project_items pi",
                        "t.project_item_id = pi.id"
                )
                .where("t.project_item_id = ?", id);

        List<Task> listTask = db.executeQuery(sql, this::map);

        return listTask.isEmpty()
                ? null
                : listTask.get(0);
    }

    @Override
    public List<Task> fetchAll() throws DatabaseException {

        Query sql = new Query()
                .select("*")
                .from("tasks t")
                .join(
                        "project_items pi",
                        "t.project_item_id = pi.id"
                );

        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(Task entity) throws DatabaseException {

        updateProjectItem(entity);

        Query sql = new Query()
                .update("tasks")
                .set("priority", entity.getPriority())
                .set("status", entity.getStatus())
                .set("start_date", entity.getStartDate())
                .set("due_date", entity.getDueDate())
                .set("completed_at", entity.getCompletedAt())
                .where("project_item_id = ?", entity.getId());
        
        
        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        return deleteProjectItem(id);
    }

    @Override
    public Task map(ResultSet rs) throws DatabaseException {

        try {

            Task t = new Task();

            // ProjectItem
            t.setId(rs.getInt("id"));
            t.setTitle(rs.getString("title"));
            t.setDescription(rs.getString("description"));
            t.setColor(rs.getString("color"));
            t.setCreatedAt(rs.getTimestamp("created_at"));
            t.setUpdatedAt(rs.getTimestamp("updated_at"));

            // Task
            t.setPriority(
                    TaskPriority.valueOf(
                            rs.getString("priority").toUpperCase()
                    )
            );

            t.setStatus(
                    TaskStatus.valueOf(
                            rs.getString("status").toUpperCase()
                    )
            );

            t.setStartDate(rs.getTimestamp("start_date"));
            t.setDueDate(rs.getTimestamp("due_date"));
            t.setCompletedAt(rs.getTimestamp("completed_at"));
            
            Project project = new Project();
            project.setId(rs.getInt("project_id"));
            t.setProject(project);
            
            User createdBy = new User();
            createdBy.setId(rs.getInt("created_by"));
            t.setCreatedBy(createdBy);
            
            int updatedById = rs.getInt("updated_by");

            if (!rs.wasNull()) {
                User updatedBy = new User();
                updatedBy.setId(updatedById);
                t.setUpdatedBy(updatedBy);
            }

            return t;

        } catch (SQLException e) {
            throw new ResultSetParsingException(
                    "Failed to parse Task from ResultSet",
                    e
            );
        }
    }
}
