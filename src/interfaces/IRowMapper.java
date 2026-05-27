/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author farel
 */
import java.sql.*;

@FunctionalInterface
public interface IRowMapper<T> {

    T map(ResultSet rs) throws SQLException;
}
