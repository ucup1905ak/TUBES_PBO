package control;

import dao.UserDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.util.List;
import model.User;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class UserControl implements IGenericControl<User, Integer> {

    private UserDAO dao = new UserDAO();

    @Override
    public int add(User user) throws DatabaseException {
        return dao.add(user);
    }

    @Override
    public User get(Integer id) throws DatabaseException {
        return dao.get(id);
    }

    @Override
    public List<User> fetchAll() throws DatabaseException {
        return dao.fetchAll();
    }

    @Override
    public int update(User user) throws DatabaseException {
        return dao.update(user);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        return dao.delete(id);
    }

    public User search(String keyword) throws DatabaseException {
        return dao.search(keyword);
    }



    public int updateProfile(User user) throws DatabaseException{
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
