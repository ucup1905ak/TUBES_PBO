package TestControl;

import dao.*;
import exception.database.DatabaseException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.*;
import model.enums.*;
import service.AuthService;
import utility.Log;

public class DummyDataSeeder {

    public static void main(String[] args) {
        System.out.println("Starting Dummy Data Seeder...");
        // Suppress logs to keep output clean
//        Log.enabled = false;

        AuthService authService = new AuthService();
        ProjectDAO projectDAO = new ProjectDAO();
        ProjectMemberDAO memberDAO = new ProjectMemberDAO();
        TaskDAO taskDAO = new TaskDAO();
        EventDAO eventDAO = new EventDAO();
        TagDAO tagDAO = new TagDAO();
        ProjectItemTagDAO itemTagDAO = new ProjectItemTagDAO();
        ProjectItemAssigneeDAO assigneeDAO = new ProjectItemAssigneeDAO();

        try {
            // 1. Users
            List<User> users = new ArrayList<>();
            long timestamp = System.currentTimeMillis();
            for (int i = 1; i <= 10; i++) {
                String username = "seeder_" + timestamp + "_user" + i;
                User user = authService.register(username, "Seeder User " + i, username + "@mail.com", "pass123");
                users.add(user);
            }
            System.out.println("Inserted 10 Users.");

            // 2. Projects & 3. Project Members
            List<Project> projects = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Project project = new Project();
                project.setName("Seeder Project " + i);
                project.setDescription("Description for project " + i);
                project.setColor("#" + i + i + i + i + i + i); // e.g. #111111
                projectDAO.add(project);
                
                List<Project> allProjects = projectDAO.fetchAll();
                Project currentProject = allProjects.get(allProjects.size() - 1);
                projects.add(currentProject);
                
                // Add owner
                memberDAO.add(currentProject.getId(), users.get(i - 1).getId(), UserRole.PROJECT_OWNER);
            }
            System.out.println("Inserted 10 Projects.");
            System.out.println("Inserted 10 Project Members (Owners).");

            // 4. Tasks (Project Items)
            List<Task> tasks = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Task task = new Task();
                task.setTitle("Seeder Task " + i);
                task.setDescription("Task description " + i);
                task.setProject(projects.get(i - 1));
                task.setCreatedBy(users.get(i - 1));
                task.setPriority(TaskPriority.MEDIUM);
                task.setStatus(TaskStatus.PENDING);
                task.setStartDate(n ew java.util.Date());
                task.setDueDate(new java.util.Date(System.currentTimeMillis() + 86400000)); // +1 day
                taskDAO.add(task);
                
                List<Task> allTasks = taskDAO.fetchAll();
                tasks.add(allTasks.get(allTasks.size() - 1));
            }
            System.out.println("Inserted 10 Tasks.");

            // 5. Events (Project Items)
            List<Event> events = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Event event = new Event();
                event.setTitle("Seeder Event " + i);
                event.setDescription("Event description " + i);
                event.setProject(projects.get(i - 1));
                event.setCreatedBy(users.get(i - 1));
                event.setLocation("Location " + i);
                event.setAllDay(false);
                event.setStartAt(new Timestamp(System.currentTimeMillis()));
                event.setEndAt(new Timestamp(System.currentTimeMillis() + 3600000)); // +1 hour
                eventDAO.add(event);
                
                List<Event> allEvents = eventDAO.fetchAll();
                events.add(allEvents.get(allEvents.size() - 1));
            }
            System.out.println("Inserted 10 Events.");

            // 6. Tags
            List<Tag> tags = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Tag tag = new Tag();
                tag.setName("Seeder Tag " + i);
                tag.setColor("#00000" + (i - 1));
                tagDAO.add(tag);
                
                List<Tag> allTags = tagDAO.fetchAll();
                tags.add(allTags.get(allTags.size() - 1));
            }
            System.out.println("Inserted 10 Tags.");

            // 7. Project Item Tags
            for (int i = 0; i < 10; i++) {
                itemTagDAO.assignTag(tasks.get(i).getId(), tags.get(i).getId());
            }
            System.out.println("Inserted 10 Project Item Tags (Assigned to Tasks).");

            // 8. Project Item Assignees
            for (int i = 0; i < 10; i++) {
                assigneeDAO.assignUser(tasks.get(i).getId(), users.get(i).getId());
            }
            System.out.println("Inserted 10 Project Item Assignees.");

            System.out.println("\n=================================");
            System.out.println("Successfully seeded all dummy data!");
            System.out.println("=================================");

        } catch (Exception e) {
            System.err.println("Seeding failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
