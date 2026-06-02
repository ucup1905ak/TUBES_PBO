package control;

import dao.UserDAO;
import exception.database.DatabaseConnectionFailedException;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.util.List;
import model.User;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class UserControl implements IGenericControl<User, Integer> {

    private UserDAO dao = new UserDAO();;



    @Override
    public int add(User user) throws DatabaseException{
        return dao.add(user);
    }

    @Override
    public User get(Integer id) {
        return dao.get(id);
    }

    @Override
    public List<User> fetchAll() {
        return dao.fetchAll();
    }

    @Override
    public int update(User user) {
        return dao.update(user);
    }

    @Override
    public int delete(Integer id) {
        return dao.delete(id);
    }

    public User getByUsername(String username) {
        return dao.getByUsername(username);
    }

    public User getByEmail(String email) {
        return dao.getByEmail(email);
    }

    public User authenticate(String email, String password) {
        User user = dao.getByEmail(email);

        if (user == null) {
            return null;
        }

        if (user.getPasswordHash().equals(password)) {
            return user;
        }

        return null;
    }

    public int updateProfile(User user) {
        User existingUser = get(user.getId());

        if (existingUser == null) {
            return 0;
        }

        existingUser.setFullName(user.getFullName());
        existingUser.setBio(user.getBio());
        existingUser.setProfilePicture(user.getProfilePicture());
        existingUser.setSocials(user.getSocials());

        return update(existingUser);
    }

    public int changePassword(Integer id, String newPassword) {
        User user = get(id);

        if (user == null) {
            return 0;
        }

        user.setPasswordHash(newPassword);

        return update(user);
    }
}
