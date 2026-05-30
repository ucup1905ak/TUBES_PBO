package dao;

import java.util.List;
import model.*;

/**
 *
 * @author Silvanus
 */

/**
 *
 * (29/5)
 * Ini kelas Test untuk DAO
 * Nanti setiap DAO bakal ada method CRUD masing"
 * - Widi
 * 
 */
 
public class TesterDAO {
    
    /**
     * USER DAO
     */
    public static void testUserCreate() {
        try {
            UserDAO dao = new UserDAO();

            User user = new User();
            user.setUsername("tes6767");
            user.setEmail("tes4747@gmail.com");
            user.setPasswordHash("kolak");
            user.setFullName("Kolak Durian");
            user.setBio("Hi manis");
            user.setProfilePicture("profile.jpg");

            dao.add(user);

            System.out.println("CREATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testUserReadAll() {
        try {
            UserDAO dao = new UserDAO();

            List<User> users = dao.fetchAll();

            System.out.println("Jumlah user: " + users.size());

            for (User user : users) {
                System.out.println(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testUserUpdate() {
        try {
            UserDAO dao = new UserDAO();

            User user = dao.get(9);

            user.setUsername("UPDATE_TEST");

            dao.update(user);

            System.out.println("UPDATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testUserDelete(int id) {
        try {
            UserDAO dao = new UserDAO();

            dao.delete(id);

            System.out.println("DELETE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * SocialDAO
     * /
}
