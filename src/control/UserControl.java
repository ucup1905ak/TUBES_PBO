package control;

import dao.UserDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.util.List;
import model.User;
import utility.security.Log;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class UserControl implements IGenericControl<User, Integer> {

    private UserDAO dao = new UserDAO();

    @Override
    public int add(User user) throws DatabaseException {
        Log.create("[Control] : Add User");
        return dao.add(user);
    }

    @Override
    public User get(Integer id) throws DatabaseException {
        Log.create("[Control] : Get 1 User");
        return dao.get(id);
    }

    @Override
    public List<User> fetchAll() throws DatabaseException {
        Log.create("[Control] : Fetch All User");
        return dao.fetchAll();
    }

    @Override
    public int update(User user) throws DatabaseException {
        Log.create("[Control] : Update User");
        return dao.update(user);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        Log.create("[Control] : Delete User From");
        return dao.delete(id);
    }

    public List<User> search(String keyword) throws DatabaseException {
        Log.create("[Control] : Search User from");
        return dao.search(keyword);
    }

    public int updateProfile(User user) throws DatabaseException {
        Log.create("[Control] : Update Profile User");
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

}
