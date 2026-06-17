/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.EventDAO;
import dao.ProjectItemAssigneeDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Event;
import model.Project;
import model.User;
import utility.security.Log;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class EventControl implements IGenericControl<Event, Integer> {

    private final EventDAO eventDAO;
    private final ProjectItemAssigneeDAO assigneeDAO;

    public EventControl() {
        this.eventDAO = new EventDAO();
        this.assigneeDAO = new ProjectItemAssigneeDAO();
        Log.create("[Control] : Init Event Control");
    }

    public int add(Event entity) throws DatabaseException {
        Log.create("[Control] : Add Event");

        if (entity == null) {
            Log.err("[Control] : Add Event failed - entity is null");
            return 0;
        }

        try {
            int rows = eventDAO.add(entity);
            Log.create("[Control] : Add Event success (" + rows + " row(s))");
            return rows;
        } catch (IllegalStateException e) {
            Log.err("[Control] : Add Event failed: " + e.getMessage());
            throw new DatabaseException("Failed to add event", e);
        }
    }

    public Event get(Integer id) throws DatabaseException {
        Log.create("[Control] : Get Event");

        if (id == null || id <= 0) {
            Log.err("[Control] : Get Event failed - id is null or invalid");
            return null;
        }

        try {
            Event event = eventDAO.get(id);
            Log.create("[Control] : Get Event success");
            return event;
        } catch (IllegalStateException e) {
            Log.err("[Control] : Get Event failed: " + e.getMessage());
            throw new DatabaseException("Failed to fetch event", e);
        }
    }

    public List<Event> fetchAll() throws DatabaseException {
        Log.create("[Control] : Fetch All Event");

        try {
            List<Event> events = eventDAO.fetchAll();
            Log.create("[Control] : Fetch All Event success (" + events.size() + " row(s))");
            return events;
        } catch (IllegalStateException e) {
            Log.err("[Control] : Fetch All Event failed: " + e.getMessage());
            throw new DatabaseException("Failed to fetch events", e);
        }
    }

    public int update(Event entity) throws DatabaseException {
        Log.create("[Control] : Update Event");

        if (entity == null || entity.getId() <= 0) {
            Log.err("[Control] : Update Event failed - entity is null or invalid id");
            return 0;
        }

        try {
            int rows = eventDAO.update(entity);
            Log.create("[Control] : Update Event success (" + rows + " row(s))");
            return rows;
        } catch (IllegalStateException e) {
            Log.err("[Control] : Update Event failed: " + e.getMessage());
            throw new DatabaseException("Failed to update event", e);
        }
    }

    public int delete(Integer id) throws DatabaseException {
        Log.create("[Control] : Delete Event");

        if (id == null || id <= 0) {
            Log.err("[Control] : Delete Event failed - id is null or invalid");
            return 0;
        }

        try {
            int rows = eventDAO.delete(id);
            Log.create("[Control] : Delete Event success (" + rows + " row(s))");
            return rows;
        } catch (IllegalStateException e) {
            Log.err("[Control] : Delete Event failed: " + e.getMessage());
            throw new DatabaseException("Failed to delete event", e);
        }
    }

    public List<Event> findByProject(Integer projectId) throws DatabaseException {
        Log.create("[Control] : Find Event By Project");

        if (projectId == null || projectId <= 0) {
            Log.err("[Control] : Find Event By Project failed - projectId is null or invalid");
            return new ArrayList<>();
        }

        try {
            Project project = new Project();
            project.setId(projectId);

            List<Event> events = eventDAO.fetchByProject(project);
            Log.create("[Control] : Find Event By Project success (" + events.size() + " row(s))");
            return events;
        } catch (IllegalStateException e) {
            Log.err("[Control] : Find Event By Project failed: " + e.getMessage());
            throw new DatabaseException("Failed to fetch events by project", e);
        }
    }

    public List<Event> search(String keyword) throws DatabaseException {
        Log.create("[Control] : Search Event");

        if (keyword == null || keyword.isBlank()) {
            Log.err("[Control] : Search Event failed - keyword is blank");
            return new ArrayList<>();
        }

        try {
            List<Event> result = new ArrayList<>();
            String searchTerm = keyword.toLowerCase();

            for (Event event : fetchAll()) {
                String title = event.getTitle() == null ? "" : event.getTitle().toLowerCase();
                String description = event.getDescription() == null ? "" : event.getDescription().toLowerCase();
                String location = event.getLocation() == null ? "" : event.getLocation().toLowerCase();

                if (title.contains(searchTerm)
                        || description.contains(searchTerm)
                        || location.contains(searchTerm)) {
                    result.add(event);
                }
            }

            Log.create("[Control] : Search Event success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Search Event failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Event> getEventsByDate(Date date) throws DatabaseException {
        Log.create("[Control] : Get Events By Date");

        if (date == null) {
            Log.err("[Control] : Get Events By Date failed - date is null");
            return new ArrayList<>();
        }

        try {
            List<Event> result = new ArrayList<>();
            LocalDate targetDate = toLocalDate(date);

            for (Event event : fetchAll()) {
                if (isEventOnDate(event, targetDate)) {
                    result.add(event);
                }
            }

            Log.create("[Control] : Get Events By Date success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Events By Date failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Event> getUpcomingEvents() throws DatabaseException {
        Log.create("[Control] : Get Upcoming Events");

        try {
            List<Event> result = new ArrayList<>();
            Timestamp now = new Timestamp(System.currentTimeMillis());

            for (Event event : fetchAll()) {
                Timestamp startAt = event.getStartAt();

                if (startAt != null && !startAt.before(now)) {
                    result.add(event);
                }
            }

            Log.create("[Control] : Get Upcoming Events success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Upcoming Events failed: " + e.getMessage());
            throw e;
        }
    }

    public boolean addParticipant(Integer eventId, User user) throws DatabaseException {
        Log.create("[Control] : Add Event Participant");

        if (eventId == null || eventId <= 0 || user == null || user.getId() <= 0) {
            Log.err("[Control] : Add Event Participant failed - invalid eventId or user");
            return false;
        }

        try {
            if (get(eventId) == null) {
                Log.err("[Control] : Add Event Participant failed - event not found");
                return false;
            }

            boolean success = assigneeDAO.assignUser(eventId, user.getId()) > 0;
            if (success) {
                Log.create("[Control] : Add Event Participant success");
            } else {
                Log.err("[Control] : Add Event Participant failed - no rows inserted");
            }
            return success;
        } catch (DatabaseException e) {
            Log.err("[Control] : Add Event Participant failed: " + e.getMessage());
            throw e;
        }
    }

    public boolean removeParticipant(Integer eventId, Integer userId) throws DatabaseException {
        Log.create("[Control] : Remove Event Participant");

        if (eventId == null || eventId <= 0 || userId == null || userId <= 0) {
            Log.err("[Control] : Remove Event Participant failed - invalid eventId or userId");
            return false;
        }

        try {
            if (get(eventId) == null) {
                Log.err("[Control] : Remove Event Participant failed - event not found");
                return false;
            }

            boolean success = assigneeDAO.removeAssignee(eventId, userId) > 0;
            if (success) {
                Log.create("[Control] : Remove Event Participant success");
            } else {
                Log.err("[Control] : Remove Event Participant failed - no rows deleted");
            }
            return success;
        } catch (DatabaseException e) {
            Log.err("[Control] : Remove Event Participant failed: " + e.getMessage());
            throw e;
        }
    }

    private boolean isEventOnDate(Event event, LocalDate targetDate) {
        if (event == null || targetDate == null) {
            return false;
        }

        Timestamp startAt = event.getStartAt();
        Timestamp endAt = event.getEndAt();

        if (startAt == null && endAt == null) {
            return false;
        }

        LocalDate startDate = startAt == null ? null : toLocalDate(startAt);
        LocalDate endDate = endAt == null ? startDate : toLocalDate(endAt);

        if (startDate == null) {
            return false;
        }

        if (endDate == null) {
            return startDate.isEqual(targetDate);
        }

        return (targetDate.isEqual(startDate) || targetDate.isAfter(startDate))
                && (targetDate.isEqual(endDate) || targetDate.isBefore(endDate));
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
