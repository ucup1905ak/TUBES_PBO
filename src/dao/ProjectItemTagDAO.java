/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import exception.database.DatabaseException;
import java.util.List;
import model.*;
/**
 *
 * @author Silvanus
 */
public class ProjectItemTagDAO{
    public int assignTag(Integer projectItemId, Integer tagId) throws DatabaseException {
        return 0;
    }

    public int removeTag(Integer projectItemId, Integer tagId) throws DatabaseException {
        return 0;
    }

    public List<Tag> getTags(Integer projectItemId) throws DatabaseException {
        return null;
    }

    public List<ProjectItem> getTaggedItems(Integer tagId) throws DatabaseException {
        return null;
    }
}
