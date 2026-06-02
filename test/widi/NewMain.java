package widi;

import dao.TesterDAO;
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
        
//        TesterDAO.testUserCreate();
//        TesterDAO.testUserReadAll();
//        TesterDAO.testUserUpdate();
//        TesterDAO.testUserDelete(9);

/**
 * (29/5)
 * 
 * DAO user done yessssssssssssssssssssssss
 * - Widi
 * 
 */ 

        TesterDAO.testSocialCreate();
        TesterDAO.testSocialReadAll();
        TesterDAO.testSocialUpdate(0);
        TesterDAO.testSocialDelete(0);
    }
    
}
