package widi;

import dao.UserDAO;
import model.User;
import model.User;
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
        
//        uDao.get();
    }
    
}
