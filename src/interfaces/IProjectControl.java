/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entity.Project;
import entity.enums.UserRole;
import java.util.List;

/**
 *
 * @author farel
 */
public interface IProjectControl {

    public List<Project> fetchUserProjects();

    public boolean setProject(int projectId);

    public Project getSelectedProject();

    public boolean editSelectedProject();

    public boolean add(Project project);

    public boolean deleteSelectedProject();

    public UserRole getUserRole();
}
