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
public class ProjectItemTagDAO{
    public int assignTag(Integer projectItemId, Integer tagId) throws SQLException {
        return 0;
    }

    public int removeTag(Integer projectItemId, Integer tagId) throws SQLException {
        return 0;
    }

    public List<Tag> getTags(Integer projectItemId) throws SQLException {
        return null;
    }

    public List<ProjectItem> getTaggedItems(Integer tagId) throws SQLException {
        return null;
    }
}
