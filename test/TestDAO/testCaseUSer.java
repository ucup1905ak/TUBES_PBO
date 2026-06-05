/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TestDAO;

import control.UserControl;
import exception.database.DatabaseException;
import java.util.List;
import model.User;
import utility.Log;

/**
 *
 * @author farel
 */
public class testCaseUSer {

    /**
     * @param args the command line arguments
     */
    
    /*
        Test Notes: (2026 - 06 - 05) Farel
        - Create User           : PASS
        - Edit User             : PASS
        - Delete User           : PASS
        - Update User           : PASS
        - Search User           : PASS
        - Update Profile        : PASS
    */
    public static void main(String[] args) {
        User farel = new User();
//        farel.setUsername("Farel");
//        farel.setEmail("udin@example.com");
//        farel.setPasswordHash("$2a$12$hashedpassword123");
//        farel.setFullName("Farelino Alexander Kim");
//        farel.setBio("Software Developer");
//        farel.setProfilePicture("profile_farel.jpg");

        UserControl control = new UserControl();
        try {
            List<User> x = control.search("udin");
            if (x == null) {
                throw new NullPointerException("User not found");
            }
//            for (User u : x) {
//                System.out.println("USER: \n" + u);
//                control.delete(u.getId());
//            }
            farel = x.get(0);
            farel.setBio("OI INI BIO GW");
            control.updateProfile(farel);

        } catch (Exception e) {
            Log.err(e.getMessage());
        }

    }

}
