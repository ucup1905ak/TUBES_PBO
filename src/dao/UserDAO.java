package dao;

import entity.Project;
import entity.User;
import interfaces.IGenericDAO;
import java.util.List;
import services.DatabaseConnection;
import utility.Query;

/**
 *
 * @author farel
 */
public class UserDAO implements IGenericDAO<User, Integer> {

    private final DatabaseConnection db;

    public UserDAO(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public int add(User entity) {
        Query sql = new Query();
        sql.insertInto("users",
                "id",
                "username",
                "email",
                "password_hash",
                "full_name",
                "bio",
                "profile_picture"
        )
                .values(
                        1,
                        "Farel",
                        "alexanderkimf@gmail.com",
                        "asdn115lndlAk2nk",
                        "Farelino Alexander Kim",
                        "This is my life full of coding",
                        "sadfsdsaasdf"
                );
        return db.executeUpdate(sql);
    }
    @Override
    public User get(Integer id) {

        Query sql = new Query()
                .select("*")
                .from("users")
                .where("id = ?", id);

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

        User user = listUser.get(0);

        SocialDAO socialDAO = new SocialDAO(db);
        user.setSocials(socialDAO.findByUserId(user.getId()));

        return user;
    }

    @Override
    public List<User> fetchAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
