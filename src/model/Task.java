/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


/**
 *
 * @author Farelino Alexander Kim / 240713000ino Alexander Kim - 240713000
 */
import entity.enums.TaskPriority;
import entity.enums.TaskStatus;
import java.util.Date;

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