package dao;

import exception.database.DatabaseException;
import exception.database.ResultSetParsingException;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.User;
import service.DatabaseConnection;
import utility.security.Log;
import utility.db.Query;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public class UserDAO implements IGenericDAO<User, Integer>, IRowMapper<User> {

    private final DatabaseConnection db = new DatabaseConnection();

    public List<User> search(String searchTerm) throws DatabaseException {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return null;
        }

        String trimmedTerm = searchTerm.trim();
        String searchPattern = "%" + trimmedTerm + "%";

        Query sql = new Query()
                .select("*")
                .from("users")
                .where("username LIKE ? OR email LIKE ? OR id = ?",
                        searchPattern, searchPattern, tryParseInt(trimmedTerm));

        try {
            List<User> results = db.executeQuery(sql, this::map);
            Log.create("UserDAO.search queried " + results.size() + " row(s).");
            return results.isEmpty() ? null : results;
        } catch (DatabaseException e) {
            Log.err("UserDAO.search failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int add(User entity) throws DatabaseException {
        try {
            Query sql = new Query();
            sql.insertInto("users",
                "username",
                "email",
                "password_hash",
                "full_name",
                "bio",
                "profile_picture"
            )
                .values(
                    entity.getUsername(),
                    entity.getEmail(),
                    entity.getPasswordHash(),
                    entity.getFullName(),
                    entity.getBio(),
                    entity.getProfilePicture()
                );
            int rows = db.executeUpdate(sql);
            Log.create("UserDAO.add updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("UserDAO.add failed: " + e.getMessage());
            throw e;
        }

    }

    //last fixed by : siapa????
    @Override
    public User get(Integer id) throws DatabaseException {
        Query sql = new Query()
                .select("*")
                .from("users")
                .where("id = ?", id);
        try {
            List<User> listUser = db.executeQuery(sql, this::map);
            Log.create("UserDAO.get queried " + listUser.size() + " row(s).");
            if (listUser.isEmpty()) {
                return null;
            }
            return listUser.get(0);
        } catch (DatabaseException e) {
            Log.err("UserDAO.get failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> fetchAll() throws DatabaseException {

        Query sql = new Query()
                .select("*")
                .from("users");
        try {
            List<User> users = db.executeQuery(sql, this::map);
            Log.create("UserDAO.fetchAll queried " + users.size() + " row(s).");
            return users;
        } catch (DatabaseException e) {
            Log.err("UserDAO.fetchAll failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(User entity) throws DatabaseException {

        try {
            Query sql = new Query()
                    .update("users")
                    .set("username", entity.getUsername())
                    .set("email", entity.getEmail())
                    .set("password_hash", entity.getPasswordHash())
                    .set("full_name", entity.getFullName())
                    .set("bio", entity.getBio())
                    .set("profile_picture", entity.getProfilePicture())
                    .set("updated_at", entity.getUpdatedAt())
                    .where("id  = ?", entity.getId());

            int rows = db.executeUpdate(sql);
            Log.create("UserDAO.update updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("UserDAO.update failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {

        try {
            Query sql = new Query()
                    .deleteFrom("users")
                    .where("id  = ?", id);

            int rows = db.executeUpdate(sql);
            Log.create("UserDAO.delete updated " + rows + " row(s).");
            return rows;
        } catch (DatabaseException e) {
            Log.err("UserDAO.delete failed: " + e.getMessage());
            throw e;
        }
    }

    public User getByEmail(String email) throws DatabaseException {
 

        Query sql = new Query()
                .select("*")
                .from("users")
                .where("email = ?", email);
//        System.out.println(sql.build());
        try {
            List<User> results = db.executeQuery(sql, this::map);
            Log.create("UserDAO.getByEmail queried " + results.size() + " row(s).");
            return results.isEmpty() ? null : results.get(0);
        } catch (DatabaseException e) {
            Log.err("UserDAO.getByEmail failed: " + e.getMessage());
            throw e;
        }

    }

    public User getByUsername(String username) throws DatabaseException {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        Query sql = new Query()
                .select("*")
                .from("users")
                .where("username = ?", username);

        try {
            List<User> results = db.executeQuery(sql, this::map);
            Log.create("UserDAO.getByUsername queried " + results.size() + " row(s).");
            return results.isEmpty() ? null : results.get(0);
        } catch (DatabaseException e) {
            Log.err("UserDAO.getByUsername failed: " + e.getMessage());
            throw e;
        }

    }

    @Override
    public User map(ResultSet rs) throws DatabaseException {
        User u = null;
        try {
            u = new User(rs.getString("username"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("password_hash"));
            u.setId(rs.getInt("id"));
            u.setBio(rs.getString("bio"));
            u.setProfilePicture(rs.getString("profile_picture"));
            u.setCreatedAt(rs.getTimestamp("created_at"));
            u.setUpdatedAt(rs.getTimestamp("updated_at"));
        } catch (SQLException e) {
            Log.err("UserDAO.map failed: " + e.getMessage());
            throw new ResultSetParsingException(
                    "Failed to parse User from ResultSet",
                    e
            );

        }

        return u;
    }

    private int tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
