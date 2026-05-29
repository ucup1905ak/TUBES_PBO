package dao;

import model.enums.TaskPriority;
import model.enums.TaskPriority;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Task;
import model.enums.TaskStatus;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class TaskDAO implements IGenericDAO<Task, Integer>, IRowMapper<Task>{

    private final DatabaseConnection db = new DatabaseConnection();
    
    /*
        (28/5)
        
        Bagian ini nyalin dari ProjectDAO.
        Semoga semigi.
    
        BTW untuk 'id' nya aku pake 'project_item_id' sesuai database
        Semoga bisa ygy
    
        - Widi
    */
    
    @Override
    public int add(Task entity) throws SQLException {
        Query sql = new Query();

        sql.insertInto("tasks",
                "priority",
                "status"
                )
                .values(
                        entity.getPriority(),
                        entity.getStatus()
                );
        return db.executeUpdate(sql);
    }

    @Override
    public Task get(Integer id) throws SQLException {
        Query sql = new Query()
                .select("*")
                .from("tasks")
                .where("project_item_id = ?", id);
        
        List<Task> listTask = db.executeQuery(sql, this::map);
        if (listTask.isEmpty()) {
            return null;
        }
        return listTask.get(0);
    }

    @Override
    public List<Task> fetchAll() throws SQLException {
        Query sql = new Query()
                .select("*")
                .from("tasks");
        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(Task entity) throws SQLException {
        Query sql = new Query()
                    .update("tasks")
                    .set("priority", entity.getPriority())
                    .set("status", entity.getStatus())
                    .set("start_date", entity.getStartDate())
                    .set("due_date", entity.getDueDate())
                    .set("completed_date", entity.getCompletedAt())
                    .where("project_item_id = ?", entity.getId());
        
        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws SQLException {
        Query sql = new Query()
                .deleteFrom("tasks")
                .where("project_item_id = ?", id);

        return db.executeUpdate(sql);
    }

    
    @Override
    public Task map(ResultSet rs) throws SQLException {
        Task t = new Task();

        t.setId(rs.getInt("project_item_id"));
        t.setPriority(TaskPriority.valueOf(rs.getString("priority").toUpperCase()));
        t.setStatus(TaskStatus.valueOf(rs.getString("status").toUpperCase()));
        t.setStartDate(rs.getTimestamp("start_date"));
        t.setDueDate(rs.getTimestamp("due_date"));
        t.setCompletedAt(rs.getTimestamp("completed_at"));
        
        return t;
    }
}
