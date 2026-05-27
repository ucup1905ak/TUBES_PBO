
import dao.UserDAO;
import entity.User;
import services.DatabaseConnection;

/**
 *
 * @author farel
 */
public class TestDatabaseConnectino {

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        UserDAO my = new UserDAO(db);
        
        my.add(new User());
        
    }
    
}
