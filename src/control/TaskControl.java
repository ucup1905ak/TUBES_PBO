/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class TaskControl extends ProjectControl {

    public boolean add(Task entity) {
        // insert task into database
        return false;
    }

    public Task get(Integer id) {
        // SELECT * FROM task WHERE id = ?
        return null;
    }

    public List<Task> fetchAll() {
        return null;
    }

    public boolean update(Task entity) {
        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public List<Task> findByProject(Integer projectId) {
        return null;
    }

    public List<Task> findByAssignee(Integer userId) {
        return null;
    }

    public List<Task> search(String keyword) {
        return null;
    }

    public boolean updateStatus(Integer taskId, TaskStatus status) {
        return false;
    }

    public boolean updatePriority(Integer taskId, TaskPriority priority) {
        return false;
    }

    public boolean markAsDone(Integer taskId) {
        return false;
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return null;
    }

    public List<Task> getTasksDueToday() {
        return null;
    }

    public List<Task> getOverdueTasks() {
        return null;
    }
}
