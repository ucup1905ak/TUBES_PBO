package dao;

import entity.User;
import interfaces.IGenericDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */
public class UserDAO implements IGenericDAO<User, Integer> {

    private final DatabaseConnection db;

    public UserDAO(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public int add(User entity) throws SQLException {
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
        return db.executeUpdate(sql);
    }

    @Override
    public User get(Integer id) throws SQLException {

        Query sql = new Query()
                .select("*")
                .from("users")
                .where("id = ?", id);
//       System.out.println(sql.build());
        User user = null;

        List<User> listUser = db.executeQuery(sql, rs -> {
            User u = new User();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setPasswordHash(rs.getString("password_hash"));
            u.setFullName(rs.getString("full_name"));
            u.setBio(rs.getString("bio"));
            u.setProfilePicture(rs.getString("profile_picture"));
            u.setCreatedAt(rs.getTimestamp("created_at"));
            u.setUpdatedAt(rs.getTimestamp("updated_at"));

            return u;
        });

        if (listUser.isEmpty()) {
            return null;
        }

        user = listUser.get(0);

        SocialDAO socialDAO = new SocialDAO(db);
        user.setSocials(socialDAO.findByUserId(user.getId()));
        return user;
    }

    @Override
    public List<User> fetchAll() throws SQLException {

        Query sql = new Query()
                .select("*")
                .from("users");
        return db.executeQuery(sql, rs -> {
            User u = new User();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setPasswordHash(rs.getString("password_hash"));
            u.setFullName(rs.getString("full_name"));
            u.setBio(rs.getString("bio"));
            u.setProfilePicture(rs.getString("profile_picture"));
            u.setCreatedAt(rs.getTimestamp("created_at"));
            u.setUpdatedAt(rs.getTimestamp("updated_at"));

            return u;
        });
    }

    @Override
    public int update(User entity) throws SQLException {

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

        return db.executeUpdate(sql);
    }

    @Override
    public int delete(Integer id) throws SQLException {

        Query sql = new Query()
                .deleteFrom("users")
                .where("id  = ?", id);

        return db.executeUpdate(sql);
    }

}
