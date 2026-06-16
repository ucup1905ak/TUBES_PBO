package TestControl;

import control.ProjectControl;
import control.SessionControl;
import model.*;


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
            
//            p.fetchUserProjects();

        } catch (Exception e) {
            System.err.println("Seeding failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
