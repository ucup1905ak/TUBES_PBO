/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expection;

import java.sql.SQLException;

/**
 *
 * @author farel
 */
public class DatabaseConnectionFailedException extends SQLException{
    
    public DatabaseConnectionFailedException(String msg){
        super("Connection failed : " + msg);
    }
}
