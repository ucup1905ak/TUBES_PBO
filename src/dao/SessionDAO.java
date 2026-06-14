package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.*;
import service.DatabaseConnection;
import utility.Log;
import utility.Query;

/**
 *
 * @author Silvanus
 */

public class SessionDAO implements IGenericDAO<Session, Integer>, IRowMapper<Session> {

    private final DatabaseConnection db = new DatabaseConnection();

    @Override
    public int add(Session entity) throws DatabaseException {
        try {
            Query sql = new Query();
            sql.insertInto("sessions",
                "token",
                "user_id",
                "created_at",
                "expires_at",
                "is_active"
            )
                .values(
                    entity.getToken(),
                    entity.getUser().getId(),
                    entity.getCreatedAt(),
                    entity.getExpiresAt(),
                    entity.isActive()
                );
            int rows = db.executeUpdate(sql);
            Log.create("SessionDAO.add updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("SessionDAO.add failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Session get(Integer id) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("sessions")
                .where("id = ?", id);
        try {
            List<Session> listSession = db.executeQuery(sql, this::map);
            Log.create("SessionDAO.get queried " + listSession.size() + " row(s).");
            if (listSession.isEmpty()) {
                return null;
            }
            return listSession.get(0);
        } catch (DatabaseException e) {
            Log.err("SessionDAO.get failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Session> fetchAll() throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("sessions");
        try {
            List<Session> sessions = db.executeQuery(sql, this::map);
            Log.create("SessionDAO.fetchAll queried " + sessions.size() + " row(s).");
            return sessions;
        } catch (DatabaseException e) {
            Log.err("SessionDAO.fetchAll failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Session entity) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("sessions")
                    .set("token", entity.getToken())
                    .set("expires_at", entity.getExpiresAt())
                    .set("is_active", entity.isActive())
                    .where("id = ?", entity.getId());
            int rows = db.executeUpdate(sql);
            Log.create("SessionDAO.update updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("SessionDAO.update failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        try {
            Query sql = new Query()
                    .deleteFrom("sessions")
                    .where("id = ?", id);
            int rows = db.executeUpdate(sql);
            Log.create("SessionDAO.delete updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("SessionDAO.delete failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Session map(ResultSet rs) throws SQLException {
        try {
            Session session = new Session();
            session.setId(rs.getInt("id"));
            session.setToken(rs.getString("token"));
            session.setCreatedAt(rs.getTimestamp("created_at"));
            session.setExpiresAt(rs.getTimestamp("expires_at"));
            session.setActive(rs.getBoolean("is_active"));
            return session;
        } catch (SQLException e) {
            Log.err("SessionDAO.map failed: " + e.getMessage());
            throw new ResultSetParsingException("Failed to parse Session from ResultSet", e);
        }
    }

    public Session getByToken(String token) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("sessions")
                .where("token = ?", token);
        try {
            List<Session> results = db.executeQuery(sql, this::map);
            Log.create("SessionDAO.getByToken queried " + results.size() + " row(s).");
            return results.isEmpty() ? null : results.get(0);
        } catch (DatabaseException e) {
            Log.err("SessionDAO.getByToken failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Session> getByUserId(int userId) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("sessions")
                .where("user_id = ?", userId);
        try {
            List<Session> sessions = db.executeQuery(sql, this::map);
            Log.create("SessionDAO.getByUserId queried " + sessions.size() + " row(s).");
            return sessions;
        } catch (DatabaseException e) {
            Log.err("SessionDAO.getByUserId failed: " + e.getMessage());
            throw e;
        }
    }
    
    public void invalidate(String token) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("sessions")
                    .set("is_active", false)
                    .where("token = ?", token);
            int rows = db.executeUpdate(sql);
            Log.create("SessionDAO.invalidate updated " + rows + " row(s).");
        } catch (DatabaseException e) {
            Log.err("SessionDAO.invalidate failed: " + e.getMessage());
            throw e;
        }
    }

    public void invalidateAllUserSessions(int userId) throws DatabaseException {
        try {
            Query sql = new Query()
                    .update("sessions")
                    .set("is_active", false)
                    .where("user_id = ?", userId);
            int rows = db.executeUpdate(sql);
            Log.create("SessionDAO.invalidateAllUserSessions updated " + rows + " row(s).");
        } catch (DatabaseException e) {
            Log.err("SessionDAO.invalidateAllUserSessions failed: " + e.getMessage());
            throw e;
        }
    }
}
