package TestDAO;

import control.SessionControl;
import utility.Log;

public class AuthTesttt {

    public static void main(String[] args) {
        SessionControl ses = new SessionControl();
        System.out.println(ses.getCurrentSession());
        
        try {
            
            ses.login("alexanderkimf@gmail.com", "password123");
            System.out.println("Logged in as "+ ses.getCurrentSession().getUser().getUsername());
            
        } catch (Exception e) {
            Log.err(e.getMessage());
        }
    }
    
}
