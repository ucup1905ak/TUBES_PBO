package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import java.time.ZoneId;
import dao.TaskDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import model.Project;
import model.Task;
import model.User;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import utility.Log;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class TaskControl implements IGenericControl<Task, Integer>{

    private final TaskDAO taskDAO;

    public TaskControl() {
        this.taskDAO = new TaskDAO();
        Log.create("[Control] : Init Task Control");
    }
    
    @Override
    public int add(Task entity) throws DatabaseException {
        Log.create("[Control] : Add Task");

        if (entity == null) {
            Log.err("[Control] : Add Task failed - entity is null");
            return 0;
        }

        try {
            int rows = taskDAO.add(entity);
            Log.create("[Control] : Add Task success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Add Task failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Task get(Integer id) throws DatabaseException {
        Log.create("[Control] : Get Task");

        if (id == null) {
            Log.err("[Control] : Get Task failed - id is null");
            return null;
        }

        try {
            Task task = taskDAO.get(id);
            Log.create("[Control] : Get Task success");
            return task;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Task failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Task> fetchAll() throws DatabaseException {
        Log.create("[Control] : Fetch All Task");

        try {
            List<Task> tasks = taskDAO.fetchAll();
            Log.create("[Control] : Fetch All Task success (" + tasks.size() + " row(s))");
            return tasks;
        } catch (DatabaseException e) {
            Log.err("[Control] : Fetch All Task failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Task entity) throws DatabaseException {
        Log.create("[Control] : Update Task");

        if (entity == null) {
            Log.err("[Control] : Update Task failed - entity is null");
            return 0;
        }

        try {
            int rows = taskDAO.update(entity);
            Log.create("[Control] : Update Task success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Update Task failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        Log.create("[Control] : Delete Task");

        if (id == null) {
            Log.err("[Control] : Delete Task failed - id is null");
            return 0;
        }

        try {
            int rows = taskDAO.delete(id);
            Log.create("[Control] : Delete Task success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Delete Task failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Task> findByProject(Integer projectId) throws DatabaseException {
        Log.create("[Control] : Find Task By Project");

        if (projectId == null) {
            Log.err("[Control] : Find Task By Project failed - projectId is null");
            return new ArrayList<>();
        }

        try {
            Project project = new Project();
            project.setId(projectId);

            List<Task> tasks = taskDAO.fetchByProject(project);
            Log.create("[Control] : Find Task By Project success (" + tasks.size() + " row(s))");
            return tasks;
        } catch (IllegalStateException e) {
            Log.err("[Control] : Find Task By Project failed: " + e.getMessage());
            throw new DatabaseException("Failed to fetch tasks by project", e);
        }
    }

    public List<Task> findByAssignee(Integer userId) throws DatabaseException {
        Log.create("[Control] : Find Task By Assignee");

        if (userId == null) {
            Log.err("[Control] : Find Task By Assignee failed - userId is null");
            return new ArrayList<>();
        }

        try {
            List<Task> result = new ArrayList<>();

            for (Task task : fetchAll()) {
                List<User> assignees = taskDAO.fetchAsignee(task);

                for (User user : assignees) {
                    if (user != null && Objects.equals(user.getId(), userId)) {
                        result.add(task);
                        break;
                    }
                }
            }

            Log.create("[Control] : Find Task By Assignee success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Find Task By Assignee failed: " + e.getMessage());
            throw e;
        } catch (IllegalStateException e) {
            Log.err("[Control] : Find Task By Assignee failed: " + e.getMessage());
            throw new DatabaseException("Failed to fetch task assignees", e);
        }
    }

    public List<Task> search(String keyword) throws DatabaseException {
        Log.create("[Control] : Search Task");

        if (keyword == null || keyword.isBlank()) {
            Log.err("[Control] : Search Task failed - keyword is blank");
            return new ArrayList<>();
        }

        try {
            List<Task> result = new ArrayList<>();
            String searchTerm = keyword.toLowerCase();

            for (Task task : fetchAll()) {
                String title = task.getTitle() == null
                        ? ""
                        : task.getTitle().toLowerCase();

                String description = task.getDescription() == null
                        ? ""
                        : task.getDescription().toLowerCase();

                if (title.contains(searchTerm)
                        || description.contains(searchTerm)) {
                    result.add(task);
                }
            }

            Log.create("[Control] : Search Task success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Search Task failed: " + e.getMessage());
            throw e;
        }
    }

    public boolean updateStatus(Integer taskId, TaskStatus status) throws DatabaseException {
        Log.create("[Control] : Update Task Status");

        if (taskId == null || status == null) {
            Log.err("[Control] : Update Task Status failed - taskId or status is null");
            return false;
        }

        try {
            Task task = get(taskId);

            if (task == null) {
                Log.err("[Control] : Update Task Status failed - task not found");
                return false;
            }

            task.setStatus(status);

            if (status == TaskStatus.DONE) {
                task.setCompletedAt(new Date());
            } else {
                task.setCompletedAt(null);
            }

            boolean success = update(task) > 0;
            if (success) {
                Log.create("[Control] : Update Task Status success");
            } else {
                Log.err("[Control] : Update Task Status failed - no rows updated");
            }
            return success;
        } catch (DatabaseException e) {
            Log.err("[Control] : Update Task Status failed: " + e.getMessage());
            throw e;
        }
    }

    public boolean updatePriority(Integer taskId, TaskPriority priority) throws DatabaseException {
        Log.create("[Control] : Update Task Priority");

        if (taskId == null || priority == null) {
            Log.err("[Control] : Update Task Priority failed - taskId or priority is null");
            return false;
        }

        try {
            Task task = get(taskId);

            if (task == null) {
                Log.err("[Control] : Update Task Priority failed - task not found");
                return false;
            }

            task.setPriority(priority);

            boolean success = update(task) > 0;
            if (success) {
                Log.create("[Control] : Update Task Priority success");
            } else {
                Log.err("[Control] : Update Task Priority failed - no rows updated");
            }
            return success;
        } catch (DatabaseException e) {
            Log.err("[Control] : Update Task Priority failed: " + e.getMessage());
            throw e;
        }
    }

    public boolean markAsDone(Integer taskId) throws DatabaseException {
        Log.create("[Control] : Mark Task As Done");

        if (taskId == null) {
            Log.err("[Control] : Mark Task As Done failed - taskId is null");
            return false;
        }

        try {
            Task task = get(taskId);

            if (task == null) {
                Log.err("[Control] : Mark Task As Done failed - task not found");
                return false;
            }

            task.markAsDone();

            boolean success = update(task) > 0;
            if (success) {
                Log.create("[Control] : Mark Task As Done success");
            } else {
                Log.err("[Control] : Mark Task As Done failed - no rows updated");
            }
            return success;
        } catch (DatabaseException e) {
            Log.err("[Control] : Mark Task As Done failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Task> getTasksByStatus(TaskStatus status) throws DatabaseException {
        Log.create("[Control] : Get Tasks By Status");

        if (status == null) {
            Log.err("[Control] : Get Tasks By Status failed - status is null");
            return new ArrayList<>();
        }

        try {
            List<Task> result = new ArrayList<>();

            for (Task task : fetchAll()) {
                if (Objects.equals(task.getStatus(), status)) {
                    result.add(task);
                }
            }

            Log.create("[Control] : Get Tasks By Status success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Tasks By Status failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Task> getTasksDueToday() throws DatabaseException {
        Log.create("[Control] : Get Tasks Due Today");

        try {
            List<Task> result = new ArrayList<>();
            LocalDate today = LocalDate.now();

            for (Task task : fetchAll()) {
                if (isSameLocalDate(task.getDueDate(), today)) {
                    result.add(task);
                }
            }

            Log.create("[Control] : Get Tasks Due Today success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Tasks Due Today failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Task> getOverdueTasks() throws DatabaseException {
        Log.create("[Control] : Get Overdue Tasks");

        try {
            List<Task> result = new ArrayList<>();
            LocalDate today = LocalDate.now();

            for (Task task : fetchAll()) {
                Date dueDate = task.getDueDate();

                if (dueDate != null
                        && !Objects.equals(task.getStatus(), TaskStatus.DONE)
                        && toLocalDate(dueDate).isBefore(today)) {
                    result.add(task);
                }
            }

            Log.create("[Control] : Get Overdue Tasks success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Overdue Tasks failed: " + e.getMessage());
            throw e;
        }
    }

    private boolean isSameLocalDate(Date date, LocalDate localDate) {
        return date != null && toLocalDate(date).isEqual(localDate);
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
