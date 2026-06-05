/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TestDAO;

import control.UserControl;
import java.util.Scanner;
import model.User;
import utility.BCrypt;
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
        createUser();
    }
    private static void createUser() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Full Name: ");
        String fullName = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = new User(
                username,
                fullName,
                email,
                BCrypt.hashpw(password, BCrypt.gensalt())
        );

        UserControl control = new UserControl();

        try {
            control.add(user);
            System.out.println("User created successfully!");
        } catch (Exception e) {
            Log.err(e.getMessage());
        }
    }
}
