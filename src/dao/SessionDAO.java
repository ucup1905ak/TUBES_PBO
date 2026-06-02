/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.*;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class SessionDAO implements IGenericDAO<Session, Integer>, IRowMapper<Session> {
    private final DatabaseConnection db = new DatabaseConnection();
    
    @Override
    public int add(Session entity) throws SQLException {
        Query sql = new Query();

        sql.insertInto("sessions").values();
        //isi
        return db.executeUpdate(sql);
    }

    @Override
    public Session get(Integer id) throws SQLException {
        Query sql = new Query();
        //isi
        List<Session> listSession = db.executeQuery(sql, this::map);
        if (listSession.isEmpty()) {
            return null;
        }
        return listSession.get(0);
    }

    @Override
    public List<Session> fetchAll() throws SQLException {
        Query sql = new Query();
        //isi
        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(Session entity) throws SQLException {
        Query sql = new Query();
        //isi
        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws SQLException {
        Query sql = new Query();
        //isi
        return db.executeUpdate(sql);
    }
    
    public Session map(ResultSet rs) throws SQLException{
        Session p = new Session();

        //isi
        return p;
    }
}
