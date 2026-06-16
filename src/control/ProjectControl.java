/*
 * Click nbfspublic//nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfspublic//nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ProjectDAO;
import dao.ProjectMemberDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.util.List;
import model.Project;
import model.User;
import model.enums.UserRole;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class ProjectControl implements IGenericControl<Project, Integer> {

    private Project selected;
    private User user;
    private ProjectDAO dao = new ProjectDAO();
    private ProjectMemberDAO mDao = new ProjectMemberDAO();

    public Project getSelected() {
        return selected;
    }

    public void setSelected(Project selected) {
        this.selected = selected;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int add(Project project) throws DatabaseException {
        return dao.add(project);
    }

    @Override
    public Project get(Integer id) throws DatabaseException {
        return dao.get(id);
    }

    @Override
    public List<Project> fetchAll() throws DatabaseException {
        return dao.fetchAll();
    }

    @Override
    public int update(Project project) throws DatabaseException {
        return dao.update(project);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        return dao.delete(id);
    }

    public List<Project> fetchUserProjects() throws DatabaseException {
        return mDao.getProjectByUser(user.getId());
    }

    public boolean setProject(Integer projectId) throws DatabaseException {
        List<Project> list = fetchUserProjects();

        for (Project p : list) {
            if (p.getId() == projectId) {
                this.selected = p;
                return true;
            }
        }
        return false;
    }

    public Project getProject() throws DatabaseException {
        return this.selected;
    }

    public boolean editSelectedProject() throws DatabaseException {
        return dao.update(this.selected) == 1? true:false;
        
    }

    public boolean deleteSelectedProject() throws DatabaseException {
        return dao.delete(this.selected.getId()) == 1? true:false;
    }

    public boolean addMember(User user) throws DatabaseException {
        return mDao.add(this.selected.getId(), user.getId(), UserRole.TEAM_MEMBER)==1? true:false;
    }

    public boolean removeMember(User user) throws DatabaseException {
        return mDao.remove(this.selected.getId(), user.getId())==1? true:false;
    }

    public List<User> getMembers() throws DatabaseException {
        return mDao.getUserByProject(this.selected.getId());
    }

    public UserRole getRole() throws DatabaseException {
        return mDao.getRole(this.selected.getId(), user.getId());
    }
    
    public User getOwner() throws DatabaseException {
        return mDao.getOwner(selected.getId());
    }
}
