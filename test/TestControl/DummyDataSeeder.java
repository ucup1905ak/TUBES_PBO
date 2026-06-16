package TestControl;

import control.ProjectControl;
import control.SessionControl;
import dao.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.*;
import model.enums.*;
import service.AuthService;

public class DummyDataSeeder {

    public static void main(String[] args) {
        System.out.println("Starting Dummy Data Seeder...");
        // Suppress logs to keep output clean
//        Log.enabled = false;

        try {
            SessionControl s = new SessionControl();
//            s.register("aaa", "LINO", "aaa@gmail.com", "123");
            s.login("aaa", "123");
            ProjectControl p = new ProjectControl(s.getCurrentUser());
            
            p.add(new Project("Keren Good", "Hidup JOKOWI"));
            p.add(new Project("Keren Good", "Hidup JOKOWI"));

        } catch (Exception e) {
            System.err.println("Seeding failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
