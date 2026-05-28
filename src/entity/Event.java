/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Farelino Alexander Kim / 240713000ino Alexander Kim - 240713000
 */
import java.sql.Timestamp;

public class Event extends ProjectItem {

    private String location;
    private boolean isAllDay;
    private Timestamp startAt;
    private Timestamp endAt;

    public Event() {
        super();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public Timestamp getStartAt() {
        return startAt;
    }

    public void setStartAt(Timestamp startAt) {
        this.startAt = startAt;
    }

    public Timestamp getEndAt() {
        return endAt;
    }

    public void setEndAt(Timestamp endAt) {
        this.endAt = endAt;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", location='" + location + '\'' +
                ", isAllDay=" + isAllDay +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", project=" + (getProject() != null ? getProject().getName() : "null") +
                ", assignee=" + getAssignee() +
                ", tags=" + getTags() +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}