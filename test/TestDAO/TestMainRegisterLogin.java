/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestDAO;

import control.SessionControl;
import control.UserControl;
import java.util.Scanner;
import model.User;
import utility.security.BCrypt;
import utility.security.Log;

/**
 *
 * @author farel
 */
public class TestMainRegisterLogin {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=== MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;

                case "2":
                    login();
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void register() {

        System.out.println("\n=== REGISTER ===");

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

    private static void login() {

        System.out.println("\n=== LOGIN ===");

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        SessionControl ses = new SessionControl();

        try {

            ses.login(email, password);

            System.out.println(
                    "Logged in as "
                    + ses.getCurrentSession()
                            .getUser()
                            .getUsername()
            );

        } catch (Exception e) {
            Log.err(e.getMessage());
        }
    }
}
