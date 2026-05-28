/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
import java.sql.*;

@FunctionalInterface
public interface IRowMapper<T> {

    public T map(ResultSet rs) throws SQLException;
}
