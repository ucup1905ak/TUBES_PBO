
import dao.UserDAO;
import entity.User;
import services.DatabaseConnection;

/**
 *
 * @author farel
 */
import java.sql.SQLException;
import utility.Log;

public class TestDatabaseConnectino {

    public static void main(String[] args) {

        DatabaseConnection db = new DatabaseConnection();
        UserDAO userDAO = new UserDAO(db);

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
