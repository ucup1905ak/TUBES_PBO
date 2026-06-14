/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TestControl;

import control.EventControl;
import control.ProjectControl;
import control.TagControl;
import control.TaskControl;
import control.UserControl;
import dao.EventDAO;
import dao.ProjectDAO;
import dao.ProjectItemAssigneeDAO;
import dao.ProjectMemberDAO;
import dao.TaskDAO;
import exception.authentication.InvalidLoginCredentialException;
import exception.validation.ValidationException;
import java.util.List;
import model.Event;
import model.Project;
import model.Session;
import model.Tag;
import model.Task;
import model.User;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import model.enums.UserRole;
import service.AuthService;

/**
 * BRD & PRD Automated Test Driver
 * 
 * @author farel
 */
public class TextControl {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("Starting MagerNoMore BRD & PRD Transaction Tests");
        System.out.println("=================================================");
        
        AuthService authService = new AuthService();
        UserControl userControl = new UserControl();
        ProjectControl projectControl = new ProjectControl();
        TaskControl taskControl = new TaskControl();
        EventControl eventControl = new EventControl();
        TagControl tagControl = new TagControl();
        ProjectMemberDAO projectMemberDAO = new ProjectMemberDAO();
        ProjectItemAssigneeDAO projectItemAsigneeDAO = new ProjectItemAssigneeDAO();
        TaskDAO taskDAO = new TaskDAO();
        EventDAO eventDAO = new EventDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        
        User user1 = null;
        User user2 = null;
        Session session1 = null;
        Project project = null;
        Task task = null;
        Event event = null;
        Tag tag1 = null;
        
        try {
            // ==========================================
            // SCENARIO 1: USER MANAGEMENT
            // ==========================================
            System.out.println("\n--- 1. Testing User Management ---");
            
            String u1Name = "testuser_" + System.currentTimeMillis();
            String u2Name = "testuser2_" + System.currentTimeMillis();
            
            System.out.println("[Test] Registering User 1...");
            user1 = authService.register(u1Name, "Test User One", u1Name + "@example.com", "password123");
            System.out.println("User 1 Registered: " + user1.getUsername() + " (ID: " + user1.getId() + ")");
            
            System.out.println("[Test] Registering User 2...");
            user2 = authService.register(u2Name, "Test User Two", u2Name + "@example.com", "password123");
            System.out.println("User 2 Registered: " + user2.getUsername() + " (ID: " + user2.getId() + ")");
            
            System.out.println("[Test] Validating Duplicate Registration Handling...");
            try {
                authService.register(u1Name, "Duplicate", u1Name + "@example.com", "pass");
                System.out.println("FAIL: Duplicate allowed!");
            } catch (ValidationException e) {
                System.out.println("Success: Duplicate rejected (" + e.getMessage() + ")");
            }

            System.out.println("[Test] Testing Login for User 1...");
            session1 = authService.authenticate(u1Name + "@example.com", "password123");
            if (session1 != null) {
                System.out.println("Success: User 1 Logged In. Token: " + session1.getToken());
            } else {
                System.out.println("FAIL: Login returned null");
            }

            System.out.println("[Test] Testing Invalid Login...");
            try {
                authService.authenticate(u1Name + "@example.com", "wrongpassword");
                System.out.println("FAIL: Invalid login allowed!");
            } catch (InvalidLoginCredentialException e) {
                System.out.println("Success: Invalid login rejected (" + e.getMessage() + ")");
            }
            
            System.out.println("[Test] Testing Change Password...");
            boolean passChanged = authService.changePassword(user1.getId(), "password123", "newpassword321");
            if (passChanged) {
                System.out.println("Success: Password changed successfully.");
            } else {
                System.out.println("FAIL: Password change failed.");
            }

            // ==========================================
            // SCENARIO 2: PROJECT COLLABORATION
            // ==========================================
            System.out.println("\n--- 2. Testing Project Collaboration ---");
            
            System.out.println("[Test] User 1 creates a new project...");
            project = new Project();
            project.setName("Test BRD Project");
            project.setDescription("Project to test PRD features");
            projectControl.add(project);
            
            List<Project> allProjects = projectDAO.fetchAll();
            if(allProjects != null && !allProjects.isEmpty()){
                project = allProjects.get(allProjects.size() - 1);
                System.out.println("Success: Project created. (ID: " + project.getId() + ")");
            }
            
            System.out.println("[Test] Setting User 1 as Project OWNER...");
            projectMemberDAO.add(project.getId(), user1.getId(), UserRole.PROJECT_OWNER);
            System.out.println("Success: User 1 added as PROJECT_OWNER");
            
            System.out.println("[Test] Adding User 2 to the Project...");
            projectMemberDAO.add(project.getId(), user2.getId(), UserRole.TEAM_MEMBER);
            System.out.println("Success: User 2 added as TEAM_MEMBER");

            // ==========================================
            // SCENARIO 3: TASK & EVENT MANAGEMENT
            // ==========================================
            System.out.println("\n--- 3. Testing Task & Event Management ---");
            
            System.out.println("[Test] Creating Task in Project...");
            task = new Task();
            task.setProject(project);
            task.setTitle("Test Validation Task");
            task.setDescription("Must complete BRD tests");
            task.setStatus(TaskStatus.PENDING);
            task.setPriority(TaskPriority.HIGH);
            task.setCreatedBy(user1);
            task.setDueDate(new java.sql.Date(System.currentTimeMillis() + 86400000)); // +1 day
            taskControl.add(task);
            
            List<Task> projectTasks = taskDAO.fetchByProject(project);
            task = projectTasks.get(projectTasks.size() - 1);
            System.out.println("Task Created: " + task.getTitle() + " (ID: " + task.getId() + ")");
            
            System.out.println("[Test] Assigning User 2 to Task...");
            projectItemAsigneeDAO.assignUser(task.getId(), user2.getId());
            System.out.println("Success: User 2 assigned to task");

            System.out.println("[Test] Updating Task Status...");
            task.setStatus(TaskStatus.IN_PROGRESS);
            taskControl.update(task);
            System.out.println("Success: Task status set to IN_PROGRESS");
            
            System.out.println("[Test] Creating Event in Project...");
            event = new Event();
            event.setProject(project);
            event.setTitle("Test Sync Meeting");
            event.setDescription("Discuss BRD issues");
            event.setCreatedBy(user1);
            event.setStartAt(new java.sql.Timestamp(System.currentTimeMillis() + 3600000));
            event.setEndAt(new java.sql.Timestamp(System.currentTimeMillis() + 7200000));
            event.setLocation("Zoom");
            eventControl.add(event);
            
            List<Event> projectEvents = eventDAO.fetchByProject(project);
            event = projectEvents.get(projectEvents.size() - 1);
            System.out.println("Event Created: " + event.getTitle() + " (ID: " + event.getId() + ")");
            
            System.out.println("[Test] Adding Tag to Task...");
            tag1 = new Tag();
            tag1.setName("Testing");
            tag1.setColor("#FF0000");
            tagControl.add(tag1);
            
            List<Tag> allTags = tagControl.fetchAll();
            tag1 = allTags.get(allTags.size() - 1);
            
            tagControl.assignTag(task.getId(), tag1.getId());
            System.out.println("Success: Assigned Tag 'Testing' to Task");

            // ==========================================
            // SCENARIO 4: VISUALIZATION SIMULATION
            // ==========================================
            System.out.println("\n--- 4. Visualizations / Retrieval ---");
            System.out.println("Simulating Table/Board View Data Retrieval:");
            List<Task> boardTasks = taskDAO.fetchByProject(project);
            for(Task t : boardTasks) {
                System.out.println(" - " + t.getTitle() + " [" + t.getStatus() + "] (Priority: " + t.getPriority() + ")");
            }
            
            System.out.println("Simulating Calendar View Data Retrieval:");
            List<Event> calEvents = eventDAO.fetchByProject(project);
            for(Event e : calEvents) {
                System.out.println(" - [" + e.getStartAt() + "] " + e.getTitle() + " at " + e.getLocation());
            }

            // ==========================================
            // CLEAN UP
            // ==========================================
            System.out.println("\n--- 5. Test Clean Up ---");
            System.out.println("[Clean] Removing tags...");
            if (tag1 != null) {
                tagControl.removeTag(task.getId(), tag1.getId());
                tagControl.delete(tag1.getId());
            }
            
            System.out.println("[Clean] Removing task & events...");
            if (task != null) taskControl.delete(task.getId());
            if (event != null) eventControl.delete(event.getId());
            
            System.out.println("[Clean] Removing project & members...");
            if (project != null) {
                projectMemberDAO.remove(project.getId(), user1.getId());
                projectMemberDAO.remove(project.getId(), user2.getId());
                projectControl.delete(project.getId());
            }
            
            System.out.println("[Clean] Removing users...");
            if (user1 != null) userControl.delete(user1.getId());
            if (user2 != null) userControl.delete(user2.getId());
            
            System.out.println("Cleanup completed.");
            
        } catch (Exception e) {
            System.out.println("\n[ERROR] Test failed with exception:");
            e.printStackTrace();
        }
        
        System.out.println("\n=================================================");
        System.out.println("Test Driver Run Completed.");
        System.out.println("=================================================");
    }
}
