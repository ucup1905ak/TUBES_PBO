
import dao.UserDAO;
import model.User;
import service.DatabaseConnection;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
import java.sql.SQLException;
import utility.Log;


//TEST YEAH
public class TestDatabaseConnectino {

    public static void main(String[] args) {

        DatabaseConnection db = new DatabaseConnection();

        // =========================
        // 1. CREATE TEST USER
        // =========================
        User user = new User(); 
        user.setUsername("yoi");
        user.setEmail("udin@test.com");
        user.setPasswordHash("123456");
        user.setFullName("Farel Test User");
        user.setBio("Testing DAO insert");
        user.setProfilePicture("profile.png");

        // =========================
        // 2. INSERT USER
        // =========================
        try {

            int result = userDAO.add(user);

            System.out.println("Insert result: " + result);

            // =========================
            // 3. FETCH ALL USERS
            // =========================
            System.out.println("\nAll Users:");
            for (User u : userDAO.fetchAll()) {
                System.out.println(u);
            }

         ////        // =========================
////        // 4. FETCH BY ID (if insert worked)
////        // =========================
////        User last = userDAO.fetchAll().get(userDAO.fetchAll().size() - 1);
////
////        User fetched = userDAO.get(last.getId());
////        System.out.println("\nFetched by ID:");
////        System.out.println(fetched);
//
//        // =========================
//        // 5. UPDATE TEST
//        // =========================
//        fetched.setFullName("Updated Name");
//        fetched.setBio("Updated Bio");
//
//        int updateResult = userDAO.update(fetched);
//        System.out.println("\nUpdate result: " + updateResult);
//
//        // =========================
//        // 6. DELETE TEST
//        // =========================
//        int deleteResult = userDAO.delete(fetched.getId());
//        System.out.println("\nDelete result: " + deleteResult);
     } catch (SQLException e) {
         Log.err("HAHAHA SETRESS");
        }
    }

}
