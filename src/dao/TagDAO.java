/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.*;
import utility.Log;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class TagDAO implements IGenericDAO<Tag, Integer>, IRowMapper<Tag>{

    @Override
    public int add(Tag entity) throws DatabaseException {
        try {
            Query sql = new Query()
                .insertInto("tags", "name", "color", "created_at")
                .values(
                    entity.getName(),
                    entity.getColor(),
                    entity.getCreatedAt()
                );

            int rows = DB.executeUpdate(sql);
            Log.create("TagDAO.add updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("TagDAO.add failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Tag get(Integer id) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("tags")
                .where("id = ?", id);

        try {
            List<Tag> listTag = DB.executeQuery(sql, this::map);
            Log.create("TagDAO.get queried " + listTag.size() + " row(s).");
            if (listTag.isEmpty()) {
                return null;
            }
            return listTag.get(0);
        } catch (DatabaseException e) {
            Log.err("TagDAO.get failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Tag> fetchAll() throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("tags");

        try {
            List<Tag> tags = DB.executeQuery(sql, this::map);
            Log.create("TagDAO.fetchAll queried " + tags.size() + " row(s).");
            return tags;
        } catch (DatabaseException e) {
            Log.err("TagDAO.fetchAll failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Tag entity) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("tags")
                    .set("name", entity.getName())
                    .set("color", entity.getColor())
                    .set("created_at", entity.getCreatedAt())
                    .where("id = ?", entity.getId());

            int rows = DB.executeUpdate(sql);
            Log.create("TagDAO.update updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("TagDAO.update failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        try {
            Query sql = new Query()
                    .deleteFrom("tags")
                    .where("id = ?", id);

            int rows = DB.executeUpdate(sql);
            Log.create("TagDAO.delete updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("TagDAO.delete failed: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public Tag map(ResultSet rs) throws DatabaseException{
        try {
            Tag tag = new Tag();
            tag.setId(rs.getInt("id"));
            tag.setName(rs.getString("name"));
            tag.setColor(rs.getString("color"));
            tag.setCreatedAt(rs.getTimestamp("created_at"));
            return tag;
        } catch (SQLException e) {
            Log.err("TagDAO.map failed: " + e.getMessage());
            throw new ResultSetParsingException(
                    "Failed to parse Tag from ResultSet",
                    e
            );
        }
    }
}
