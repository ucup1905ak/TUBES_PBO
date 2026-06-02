/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.management.relation.Role;
import model.*;
import service.DatabaseConnection;
import utility.Query;

/**
 *
 * @author Silvanus
 */
public class ProjectItemAsigneeDAO{
    public int assignUser(Integer projectItemId, Integer userId) throws SQLException {
        return 0;
    }

    public int removeAssignee(Integer projectItemId, Integer userId) throws SQLException {
        return 0;
    }

    public List<User> getAssignees(Integer projectItemId) throws SQLException {
        return null;
    }

    public List<ProjectItem> getAssignedItems(Integer userId) throws SQLException {
        return null;
    }
}
