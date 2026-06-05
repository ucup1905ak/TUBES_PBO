/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.util.List;
import model.*;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class AttachmentDAO implements IGenericDAO<Attachment, Integer>, IRowMapper<Attachment>{
    private final DatabaseConnection db = new DatabaseConnection();
    
    @Override
    public int add(Attachment entity) throws DatabaseException {
        Query sql = new Query();

        sql.insertInto("attachment").values();
        //isi
        return db.executeUpdate(sql);
    }

    @Override
    public Attachment get(Integer id) throws DatabaseException {
        Query sql = new Query();
        //isi
        List<Attachment> listAttachment = db.executeQuery(sql, this::map);
        if (listAttachment.isEmpty()) {
            return null;
        }
        return listAttachment.get(0);
    }

    @Override
    public List<Attachment> fetchAll() throws DatabaseException {
        Query sql = new Query();
        //isi
        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(Attachment entity) throws DatabaseException {
        Query sql = new Query();
        //isi
        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        Query sql = new Query();
        //isi
        return db.executeUpdate(sql);
    }
    
    public Attachment map(ResultSet rs) throws DatabaseException{
        Attachment p = new Attachment();

        //isi
        return p;
    }
}
