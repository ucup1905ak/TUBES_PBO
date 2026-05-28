package dao;

import entity.Project;
import entity.User;
import interfaces.IGenericDAO;
import interfaces.IRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */

//TEASTTEFEUFYUE
public class UserDAO implements IGenericDAO<User, Integer>, IRowMapper<User>{

    private final DatabaseConnection db = new DatabaseConnection();

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
        List<User> listUser = db.executeQuery(sql, this::map);
        if (listUser.isEmpty()) {
            return null;
        }
        return listUser.get(0);
    }

    @Override
    public List<User> fetchAll() throws SQLException {

        Query sql = new Query()
                .select("*")
                .from("users");
        return db.executeQuery(sql, this::map);
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
    
//    @Override 
//    public SocialLink map(ResultSet rs) throws SQLException {
//        SocialLink link = new SocialLink(
//                SocialPlatform.valueOf(rs.getString("platform")),
//                rs.getString("url")
//        );
//        
//        link.setId(rs.getInt("id"));
//        //awalnya disini ada add usernya ke dalam social. Tapi itu tidak KOHSESI (SOLID)
//        return link;
//    }

    
    public User map(ResultSet rs) throws SQLException{
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
    }
}
