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
public class ProjectItemDAO implements IGenericDAO<ProjectItem, Integer>, IRowMapper<ProjectItem>{
    private final DatabaseConnection db = new DatabaseConnection();
    
    @Override
    public int add(ProjectItem entity) throws SQLException {
        Query sql = new Query();

        sql.insertInto("project_item").values();
        //isi
        return db.executeUpdate(sql);
    }

    @Override
    public ProjectItem get(Integer id) throws SQLException {
        Query sql = new Query();
        //isi
        List<ProjectItem> listProjectItem = db.executeQuery(sql, this::map);
        if (listProjectItem.isEmpty()) {
            return null;
        }
        return listProjectItem.get(0);
    }

    @Override
    public List<ProjectItem> fetchAll() throws SQLException {
        Query sql = new Query();
        //isi
        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(ProjectItem entity) throws SQLException {
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
    
    @Override
    public ProjectItem map(ResultSet rs) throws SQLException{
        ProjectItem p = new Task();

        //isi
        return p;
    }
}
