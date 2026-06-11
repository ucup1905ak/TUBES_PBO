package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import model.Social;
import model.enums.SocialPlatform;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.*;
import java.util.List;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public class SocialDAO implements IGenericDAO<Social, Integer>, IRowMapper<Social> {

    private final DatabaseConnection db = new DatabaseConnection();

    @Override
    public int add(Social entity) throws DatabaseException {
        Query sql = new Query()
                .insertInto("social_links",
                        "user_id", "platform", "url")
                .values(
                        entity.getUser().getId(),
                        entity.getPlatform(),
                        entity.getUrl()
                );
        return db.executeUpdate(sql);
    }

    @Override
    public Social get(Integer id) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("social_links")
                .where("id", id);

        return db.executeQuery(sql, this::map).get(0);
    }

    @Override
    public List<Social> fetchAll() throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("social_links");

        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(Social entity) throws DatabaseException {
        Query sql = new Query()
                .update("social_links")
                .set("platform", entity.getPlatform().name())
                .set("url", entity.getUrl())
                .where("id", entity.getId());

        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        Query sql = new Query()
                .deleteFrom("social_links")
                .where("id", id);

        return db.executeUpdate(sql);
    }

    public List<Social> findByUserId(int userId) throws SQLException {
        Query sql = new Query()
                .select("*")
                .from("social_links")
                .where("user_id", userId);

        return db.executeQuery(sql, this::map);
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
            throw new ResultSetParsingException(
                    "Failed to parse User from ResultSet",
                    e
            );
        }
    }

}
