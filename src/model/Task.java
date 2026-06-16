/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Farelino Alexander Kim / 240713000ino Alexander Kim - 240713000
 */
import java.util.Date;
import model.enums.TaskPriority;
import model.enums.TaskStatus;

public class Task extends ProjectItem {

    private TaskPriority priority;
    private TaskStatus status;
    private Date startDate;
    private Date dueDate;
    private Date completedAt;

    public Task() {
        super();
        this.status = TaskStatus.PENDING;
    }

    public Task(
            String title,
            String description,
            Project project,
            User createdBy,
            TaskPriority priority,
            TaskStatus status,
            Date startDate,
            Date dueDate
    ) {
        super();
        setTitle(title);
        setDescription(description);
        setProject(project);
        setCreatedBy(createdBy);
        this.priority = priority;
        this.status = status != null ? status : TaskStatus.PENDING;
        this.startDate = startDate;
        this.dueDate = dueDate;
        if (this.status == TaskStatus.DONE) {
            this.completedAt = startDate != null ? startDate : new Date();
        }
    }
    

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public void markAsDone() {
        this.status = TaskStatus.DONE;
        this.completedAt = new Date();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", completedAt=" + completedAt +
                ", project=" + (getProject() != null ? getProject().getName() : "null") +
                ", assignee=" + getAssignee() +
                ", tags=" + getTags() +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}