package dao;

import entity.SocialLink;
import entity.enums.SocialPlatform;
import interfaces.IGenericDAO;
import java.util.List;
import services.DatabaseConnection;
import utility.Query;

/**
 *
 * @author farel
 */
public class SocialDAO implements IGenericDAO<SocialLink, Integer> {

    private final DatabaseConnection db;

    public SocialDAO(DatabaseConnection db) {
        this.db = db;
    }
    

    @Override
    public int add(SocialLink entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SocialLink get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SocialLink> fetchAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(SocialLink entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<SocialLink> findByUserId(int userId) {
        Query sql = new Query()
                .select("*")
                .from("social_links")
                .where("user_id = ?", userId);

        return db.executeQuery(sql, rs -> {
            SocialLink s = new SocialLink(
                    SocialPlatform.valueOf(rs.getString("platform")),
                    rs.getString("url")
            );

            s.setId(rs.getInt("id"));
            return s;
        });
    }
}
