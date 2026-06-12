package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import model.enums.TaskPriority;
import interfaces.IGenericDAO;
import interfaces.IProjectItemDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.User;
import model.Project;
import model.ProjectItem;
import model.enums.TaskStatus;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class TaskDAO implements IProjectItemDAO, IRowMapper<Task> {
    
    
    @Override
    public List<ProjectItem> fetchByProject(Project id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> fetchAsignee(ProjectItem id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int add(ProjectItem entity) throws DatabaseException {
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

        DB.executeUpdate(sql);

        return projectItemId;

    }

    @Override
    public ProjectItem get(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ProjectItem> fetchAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(ProjectItem entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Task map(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    /*
        (5/6)
    
        Ini Turunan dari ProjectItemDAO
        semoga work amin
    
        - Widi
     */
    
    
    
    
}
