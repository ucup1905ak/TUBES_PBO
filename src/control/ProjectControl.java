/*
 * Click nbfspublic//nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfspublic//nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ProjectDAO;
import dao.ProjectMemberDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import model.User;
import model.enums.UserRole;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class ProjectControl implements IGenericControl<Project, Integer> {

    private static Project selected;
    private final User user;
    private final  ProjectDAO dao = new ProjectDAO();
    private final  ProjectMemberDAO mDao = new ProjectMemberDAO();

    public ProjectControl(User user) {
        this.user = user;
    }

    /**
     * Returns currently selected project for this running session.
     */
    public Project getSelected() {
        return selected;
    }

    /**
     * Sets current selected project for this running session.
     */
    public void setSelected(Project selected) {
        ProjectControl.selected = selected;
    }

    public User getUser() {
        return user;
    }

    /**
     * Ensures there is always a valid selected project.
     * If current selection is null or no longer accessible, it picks the first
     * accessible project from the current user's project list.
     */
    private void ensureSelectedProject() throws DatabaseException {
        List<Project> list = fetchUserProjects(user);
        if (list == null || list.isEmpty()) {
            selected = null;
            return;
        }

        if (selected == null) {
            selected = findFirstProject(list);
            return;
        }

        for (Project p : list) {
            if (p.getId() == selected.getId()) {
                return;
            }
        }

        selected = findFirstProject(list);
    }

    /**
     * Returns a non-stale selected project when available.
     */
    public Project resolveSelectedProject() throws DatabaseException {
        ensureSelectedProject();
        return selected;
    }

    /**
     * Selects the first non-null project from list.
     */
    private Project findFirstProject(List<Project> list) {
        for (Project p : list) {
            if (p != null) {
                return p;
            }
        }
        return null;
    }

    @Override
    public int  add(Project project) throws DatabaseException {
        int pid = dao.add(project);
        int userId = user.getId();

        mDao.add(pid, userId, UserRole.PROJECT_OWNER);
        selected = dao.get(pid);
        return 1;
    }

    @Override
    public Project get(Integer id) throws DatabaseException {
        Project p =  dao.get(id);
        p.setMembers(mDao.getUserByProject(p.getId()));
        return p;
    }

    @Override
    public List<Project> fetchAll() throws DatabaseException {
        List<Project> list = dao.fetchAll();

        for (Project p : list) {
            p.setMembers(mDao.getUserByProject(p.getId()));
        }
        return list;
    }

    @Override
    public int update(Project project) throws DatabaseException {
        return dao.update(project);
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        int result = dao.delete(id);
        if (selected != null && selected.getId() == id) {
            selected = null;
            ensureSelectedProject();
        }
        return result;
    }

    public List<Project> fetchUserProjects(User u) throws DatabaseException {
        if (u == null) {
            return new ArrayList<>();
        }
        List<Project> list = mDao.getProjectByUser(u.getId());
        for (Project p : list) {    
            p.setMembers(mDao.getUserByProject(p.getId()));
        }
        return list;
    }

    public boolean setProject(Integer projectId) throws DatabaseException {
        List<Project> list = fetchUserProjects(user);
        Project p = dao.get(projectId);
        if (list == null || p == null) {
            return false;
        }

        boolean allowed = false;
        for (Project item : list) {
            if (item != null && item.getId() == p.getId()) {
                allowed = true;
                break;
            }
        }

        if (!allowed) {
            return false;
        }
        selected = p;
        return true;
    }

    public Project getProject() throws DatabaseException {
        return resolveSelectedProject();
    }

    public boolean editSelectedProject() throws DatabaseException {
        return dao.update(selected) == 1;
        
    }

    public boolean deleteSelectedProject() throws DatabaseException {
        return dao.delete(selected.getId()) == 1;
    }

    public boolean addMember(User user) throws DatabaseException {
        return mDao.add(selected.getId(), user.getId(), UserRole.TEAM_MEMBER)==1;
    }

    public boolean removeMember(User user) throws DatabaseException {
        return mDao.remove(selected.getId(), user.getId())==1;
    }

    public List<User> getMembers() throws DatabaseException {
        return mDao.getUserByProject(selected.getId());
    }

    public UserRole getRole(User u) throws DatabaseException {
        return mDao.getRole(selected.getId(), u.getId());
    }
    
    public User getOwner() throws DatabaseException {
        if (this.selected == null) {
            return null;
        }
        return mDao.getOwner(selected.getId());
    }
}
