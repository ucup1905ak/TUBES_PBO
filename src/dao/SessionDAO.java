package dao;

import exception.database.DatabaseException;
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
    public int add(Session entity) throws DatabaseException {
        Query sql = new Query();
        sql.insertInto("sessions",
                "token",
                "userId",
                "createdAt",
                "expiresAt",
                "isActive"
        )
                .values(
                        entity.getToken(),
                        entity.getUser().getId(),
                        entity.getCreatedAt(),
                        entity.getExpiresAt(),
                        entity.isActive()
                );
        return db.executeUpdate(sql);
    }

    @Override
    public Session get(Integer id) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("sessions")
                .where("id = ?", id);
        List<Session> listSession = db.executeQuery(sql, this::map);
        if (listSession.isEmpty()) {
            return null;
        }
        return listSession.get(0);
    }

    @Override
    public List<Session> fetchAll() throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("sessions");
        return db.executeQuery(sql, this::map);
    }

    @Override
    public int update(Session entity) throws DatabaseException {
        Query sql = new Query()
                .update("sessions")
                .set("token", entity.getToken())
                .set("expiresAt", entity.getExpiresAt())
                .set("isActive", entity.isActive())
                .where("id = ?", entity.getId());
        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        Query sql = new Query()
                .deleteFrom("sessions")
                .where("id = ?", id);
        return db.executeUpdate(sql);
    }

    public Session map(ResultSet rs) throws SQLException {
        Session session = new Session();
        session.setId(rs.getInt("id"));
        session.setToken(rs.getString("token"));
        session.setCreatedAt(rs.getTimestamp("createdAt"));
        session.setExpiresAt(rs.getTimestamp("expiresAt"));
        session.setActive(rs.getBoolean("isActive"));
        return session;
    }

    public Session getByToken(String token) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("sessions")
                .where("token = ?", token);
        List<Session> results = db.executeQuery(sql, this::map);
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Session> getByUserId(int userId) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("sessions")
                .where("userId = ?", userId);
        return db.executeQuery(sql, this::map);
    }
    
    public void invalidate(String token) throws DatabaseException {
        Query sql = new Query()
                .update("sessions")
                .set("isActive", false)
                .where("token = ?", token);
        db.executeUpdate(sql);
    }

    public void invalidateAllUserSessions(int userId) throws DatabaseException {
        Query sql = new Query()
                .update("sessions")
                .set("isActive", false)
                .where("userId = ?", userId);
        db.executeUpdate(sql);
    }
}
