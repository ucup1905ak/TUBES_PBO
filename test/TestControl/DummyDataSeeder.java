package TestControl;

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
            
        } catch (Exception e) {
            System.err.println("Seeding failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
