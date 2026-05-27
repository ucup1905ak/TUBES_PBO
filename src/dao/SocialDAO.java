package dao;

import entity.SocialLink;
import entity.enums.SocialPlatform;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.*;
import java.util.List;
import services.DatabaseConnection;
import utility.Query;

/**
 *
 * @author farel
 */
public class SocialDAO implements IGenericDAO<SocialLink, Integer>, IRowMapper<SocialLink> {

    private final DatabaseConnection db;

    public SocialDAO(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public int add(SocialLink entity) throws SQLException {
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
    public SocialLink get(Integer id) throws SQLException {
        Query sql = new Query()
                .select("*")
                .from("social_links")
                .where("id", id);

        return db.executeQuery(sql, this::map).get(0);
    }

    @Override
    public List<SocialLink> fetchAll() throws SQLException {
        Query sql = new Query()
                .select("*")
                .from("social_links");

        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(SocialLink entity) throws SQLException {
        Query sql = new Query()
                .update("social_links")
                .set("platform", entity.getPlatform().name())
                .set("url", entity.getUrl())
                .where("id", entity.getId());

        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws SQLException {
        Query sql = new Query()
                .deleteFrom("social_links")
                .where("id", id);

        return db.executeUpdate(sql);
    }

    public List<SocialLink> findByUserId(int userId) throws SQLException {
        Query sql = new Query()
                .select("*")
                .from("social_links")
                .where("user_id", userId);

        return db.executeQuery(sql, this::map);
    }

 

    @Override
    public SocialLink map(ResultSet rs) throws SQLException {
        UserDAO user = new UserDAO(db);
        SocialLink link = new SocialLink(
                SocialPlatform.valueOf(rs.getString("platform")),
                rs.getString("url")
        );
        
        link.setId(rs.getInt("id"));
        link.setUser(user.get(rs.getInt("user_id")));

        return link;
    }

}
