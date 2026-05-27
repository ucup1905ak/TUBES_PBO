/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
import interfaces.IAssignable;
import interfaces.IAuditable;
import interfaces.ITaggable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public abstract class ProjectItem implements IAssignable, ITaggable, IAuditable{

    private int id;
    private String title;
    private String description;
    private String color;
    private Project project;
    private List<Tag> tags;
    private List<Attachment> attachments;
    private Timestamp createdAt;
    private User createdBy;
    private Timestamp updatedAt;
    private User updatedBy;
    private List<User> assignee;

    public ProjectItem() {
        this.tags = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.assignee = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<User> getAssignee() {
        return assignee;
    }

    public void setAssignee(List<User> assignee) {
        this.assignee = assignee;
    }

    public void addAssignee(User assignee) {
        this.assignee.add(assignee);
    }

    public void removeAssignee(User assignee) {
        this.assignee.remove(assignee);
    }

    @Override
    public String toString() {
        return "ProjectItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", project=" + (project != null ? project.getName() : "null") +
                ", tags=" + tags +
                ", attachments=" + attachments +
                ", createdAt=" + createdAt +
                ", createdBy=" + (createdBy != null ? createdBy.getUsername() : "null") +
                ", updatedAt=" + updatedAt +
                ", updatedBy=" + (updatedBy != null ? updatedBy.getUsername() : "null") +
                ", assignee=" + assignee +
                '}';
    }
}