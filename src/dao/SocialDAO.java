package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.*;
import java.util.List;
import model.Social;
import model.enums.SocialPlatform;
import service.DatabaseConnection;
import utility.Log;
import utility.Query;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public class SocialDAO implements IGenericDAO<Social, Integer>, IRowMapper<Social> {

    private final DatabaseConnection db = new DatabaseConnection();

    @Override
    public int add(Social entity) throws DatabaseException {
        try {
            Query sql = new Query()
                .insertInto("social_links",
                    "user_id", "platform", "url")
                .values(
                    entity.getUser().getId(),
                    entity.getPlatform(),
                    entity.getUrl()
                );
            int rows = db.executeUpdate(sql);
            Log.create("SocialDAO.add updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("SocialDAO.add failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Social get(Integer id) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("social_links")
                .where("id", id);

        try {
            List<Social> socials = db.executeQuery(sql, this::map);
            Log.create("SocialDAO.get queried " + socials.size() + " row(s).");
            return socials.isEmpty() ? null : socials.get(0);
        } catch (DatabaseException e) {
            Log.err("SocialDAO.get failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Social> fetchAll() throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("social_links");

        try {
            List<Social> socials = db.executeQuery(sql, this::map);
            Log.create("SocialDAO.fetchAll queried " + socials.size() + " row(s).");
            return socials;
        } catch (DatabaseException e) {
            Log.err("SocialDAO.fetchAll failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Social entity) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("social_links")
                    .set("platform", entity.getPlatform().name())
                    .set("url", entity.getUrl())
                    .where("id", entity.getId());

            int rows = db.executeUpdate(sql);
            Log.create("SocialDAO.update updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("SocialDAO.update failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        try {
            Query sql = new Query()
                    .deleteFrom("social_links")
                    .where("id", id);

            int rows = db.executeUpdate(sql);
            Log.create("SocialDAO.delete updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("SocialDAO.delete failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Social> findByUserId(int userId) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("social_links")
                .where("user_id", userId);
        try {
            List<Social> socials = db.executeQuery(sql, this::map);
            Log.create("SocialDAO.findByUserId queried " + socials.size() + " row(s).");
            return socials;
        } catch (DatabaseException e) {
            Log.err("SocialDAO.findByUserId failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Social map(ResultSet rs) throws DatabaseException {
        try {

            Social link = new Social(
                    SocialPlatform.valueOf(rs.getString("platform")),
                    rs.getString("url")
            );

            link.setId(rs.getInt("id"));
            //awalnya disini ada add usernya ke dalam social. Tapi itu tidak KOHSESI (SOLID)
            return link;
        } catch (SQLException e) {
            Log.err("SocialDAO.map failed: " + e.getMessage());
            throw new ResultSetParsingException(
                    "Failed to parse User from ResultSet",
                    e
            );
        }
    }

}
