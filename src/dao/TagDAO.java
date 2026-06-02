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
public class TagDAO implements IGenericDAO<Tag, Integer>, IRowMapper<Tag>{
    private final DatabaseConnection db = new DatabaseConnection();
    
    @Override
    public int add(Tag entity) throws SQLException {
        Query sql = new Query();

        sql.insertInto("tag").values();
        //isi
        return db.executeUpdate(sql);
    }

    @Override
    public Tag get(Integer id) throws SQLException {
        Query sql = new Query();
        //isi
        List<Tag> listTag = db.executeQuery(sql, this::map);
        if (listTag.isEmpty()) {
            return null;
        }
        return listTag.get(0);
    }

    @Override
    public List<Tag> fetchAll() throws SQLException {
        Query sql = new Query();
        //isi
        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(Tag entity) throws SQLException {
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
    
    public Tag map(ResultSet rs) throws SQLException{
        Tag p = new Tag();

        //isi
        return p;
    }
}
