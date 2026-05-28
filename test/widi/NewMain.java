package widi;

import dao.UserDAO;
import entity.User;
import service.DatabaseConnection;

/**
 *
 * @author Silvanus
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DatabaseConnection db = new DatabaseConnection();
        User u = null;
        
        UserDAO uDao = new UserDAO();
        
        
    }
    
}
