package interfaces;

import java.util.List;
import model.Project;
import model.ProjectItem;
import model.User;

/**
 *
 * @author Silvanus
 */
public interface IProjectItemDAO  extends IGenericDAO<ProjectItem, Integer>{
    
    public List<ProjectItem> fetchByProject(Project id); //PROJECT to PROJECT ITEM (1 ... M)
    public List<User> fetchAsignee(ProjectItem id); //PROJECT ITEM to USER (M ... M)
}
