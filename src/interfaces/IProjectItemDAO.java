package interfaces;

import java.util.List;
import model.Project;
import model.User;

/**
 *
 * @author Silvanus
 */
public interface IProjectItemDAO<T>  extends IGenericDAO<T, Integer>{
    
    public List<T> fetchByProject(Project id); //PROJECT to PROJECT ITEM (1 ... M)
    public List<User> fetchAsignee(T id); //PROJECT ITEM to USER (M ... M)
}
