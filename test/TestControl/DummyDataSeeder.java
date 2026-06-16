package TestControl;

import control.ProjectControl;
import control.SessionControl;
import control.TaskControl;
import java.util.Calendar;
import java.util.Date;
import model.*;
import model.enums.TaskPriority;
import model.enums.TaskStatus;


public class DummyDataSeeder {

    public static void main(String[] args) {
        System.out.println("Starting Dummy Data Seeder...");
        // Suppress logs to keep output clean
//        Log.enabled = false;

        try {
            
            SessionControl s = new SessionControl();
//            s.register("aaa", "LINO", "aaa@gmail.com", "123");
            s.login("ucup@x.com", "123");
            ProjectControl p = new ProjectControl(s.getCurrentUser());

            TaskControl t = new TaskControl();
            Project selectedProject = p.resolveSelectedProject();

            if (selectedProject == null) {
                System.err.println("No project found for current user. Create a project first.");
                return;
            }

            Date now = new Date();

            Task[] examples = new Task[]{
                new Task("Setup repo structure", "Initialize folders and base classes", selectedProject, s.getCurrentUser(), TaskPriority.HIGH, TaskStatus.PENDING, now, addDays(now, 1)),
                new Task("Design login UI", "Create login form and validation flow", selectedProject, s.getCurrentUser(), TaskPriority.MEDIUM, TaskStatus.IN_PROGRESS, now, addDays(now, 2)),
                new Task("Implement auth control", "Connect login form to SessionControl", selectedProject, s.getCurrentUser(), TaskPriority.HIGH, TaskStatus.PENDING, now, addDays(now, 3)),
                new Task("Write DAO smoke test", "Add simple CRUD check for ProjectDAO", selectedProject, s.getCurrentUser(), TaskPriority.MEDIUM, TaskStatus.DONE, now, addDays(now, 4)),
                new Task("Polish dashboard", "Improve spacing and project list rendering", selectedProject, s.getCurrentUser(), TaskPriority.LOW, TaskStatus.PENDING, now, addDays(now, 5))
            };

            int success = 0;
            for (Task task : examples) {
                success += t.add(task);
            }

            System.out.println("Inserted " + success + " of 5 example task(s) into project: " + selectedProject.getName());

        } catch (Exception e) {
            System.err.println("Seeding failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Date addDays(Date base, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(base);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
}
