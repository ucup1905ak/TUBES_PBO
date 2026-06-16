/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.EventDAO;
import dao.ProjectItemTagDAO;
import dao.TagDAO;
import dao.TaskDAO;
import exception.database.DatabaseException;
import interfaces.IGenericControl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Event;
import model.Project;
import model.ProjectItem;
import model.Tag;
import model.Task;
import utility.security.Log;

/**
 *
 * @author Farelino Alexander Kim - 240713000
 */
public class TagControl implements IGenericControl<Tag, Integer> {

    private final TagDAO tagDAO;
    private final ProjectItemTagDAO projectItemTagDAO;

    public TagControl() {
        this.tagDAO = new TagDAO();
        this.projectItemTagDAO = new ProjectItemTagDAO();
        Log.create("[Control] : Init Tag Control");
    }

    @Override
    public int add(Tag entity) throws DatabaseException {
        Log.create("[Control] : Add Tag");

        if (entity == null) {
            Log.err("[Control] : Add Tag failed - entity is null");
            return 0;
        }

        try {
            int rows = tagDAO.add(entity);
            Log.create("[Control] : Add Tag success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Add Tag failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Tag get(Integer id) throws DatabaseException {
        Log.create("[Control] : Get Tag");

        if (id == null) {
            Log.err("[Control] : Get Tag failed - id is null");
            return null;
        }

        try {
            Tag tag = tagDAO.get(id);
            Log.create("[Control] : Get Tag success");
            return tag;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Tag failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Tag> fetchAll() throws DatabaseException {
        Log.create("[Control] : Fetch All Tag");

        try {
            List<Tag> tags = tagDAO.fetchAll();
            Log.create("[Control] : Fetch All Tag success (" + tags.size() + " row(s))");
            return tags;
        } catch (DatabaseException e) {
            Log.err("[Control] : Fetch All Tag failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Tag entity) throws DatabaseException {
        Log.create("[Control] : Update Tag");

        if (entity == null) {
            Log.err("[Control] : Update Tag failed - entity is null");
            return 0;
        }

        try {
            int rows = tagDAO.update(entity);
            Log.create("[Control] : Update Tag success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Update Tag failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(Integer id) throws DatabaseException {
        Log.create("[Control] : Delete Tag");

        if (id == null) {
            Log.err("[Control] : Delete Tag failed - id is null");
            return 0;
        }

        try {
            int rows = tagDAO.delete(id);
            Log.create("[Control] : Delete Tag success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Delete Tag failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Tag> search(String keyword) throws DatabaseException {
        Log.create("[Control] : Search Tag");

        if (keyword == null || keyword.isBlank()) {
            Log.err("[Control] : Search Tag failed - keyword is blank");
            return new ArrayList<>();
        }

        try {
            List<Tag> result = new ArrayList<>();
            String searchTerm = keyword.toLowerCase();

            for (Tag tag : fetchAll()) {
                String name = tag.getName() == null
                        ? ""
                        : tag.getName().toLowerCase();

                if (name.contains(searchTerm)) {
                    result.add(tag);
                }
            }

            Log.create("[Control] : Search Tag success (" + result.size() + " row(s))");
            return result;
        } catch (DatabaseException e) {
            Log.err("[Control] : Search Tag failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Tag> findByProject(Integer projectId) throws DatabaseException {
        Log.create("[Control] : Find Tag By Project");

        if (projectId == null) {
            Log.err("[Control] : Find Tag By Project failed - projectId is null");
            return new ArrayList<>();
        }

        try {
            List<Tag> projectTags = new ArrayList<>();
            TaskDAO taskDAO = new TaskDAO();
            EventDAO eventDAO = new EventDAO();

            Project project = new Project();
            project.setId(projectId);

            List<Task> tasks = taskDAO.fetchByProject(project);
            List<Event> events = eventDAO.fetchByProject(project);

            Set<Integer> uniqueTagIds = new HashSet<>();

            for (Task task : tasks) {
                List<Tag> tags = projectItemTagDAO.getTags(task.getId());
                for (Tag tag : tags) {
                    if (uniqueTagIds.add(tag.getId())) {
                        projectTags.add(tag);
                    }
                }
            }

            for (Event event : events) {
                List<Tag> tags = projectItemTagDAO.getTags(event.getId());
                for (Tag tag : tags) {
                    if (uniqueTagIds.add(tag.getId())) {
                        projectTags.add(tag);
                    }
                }
            }

            Log.create("[Control] : Find Tag By Project success (" + projectTags.size() + " tag(s))");
            return projectTags;
        } catch (DatabaseException e) {
            Log.err("[Control] : Find Tag By Project failed: " + e.getMessage());
            throw e;
        }
    }

    public int assignTag(Integer projectItemId, Integer tagId) throws DatabaseException {
        Log.create("[Control] : Assign Tag to Project Item");

        if (projectItemId == null || tagId == null) {
            Log.err("[Control] : Assign Tag failed - projectItemId or tagId is null");
            return 0;
        }

        try {
            int rows = projectItemTagDAO.assignTag(projectItemId, tagId);
            Log.create("[Control] : Assign Tag success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Assign Tag failed: " + e.getMessage());
            throw e;
        }
    }

    public int removeTag(Integer projectItemId, Integer tagId) throws DatabaseException {
        Log.create("[Control] : Remove Tag from Project Item");

        if (projectItemId == null || tagId == null) {
            Log.err("[Control] : Remove Tag failed - projectItemId or tagId is null");
            return 0;
        }

        try {
            int rows = projectItemTagDAO.removeTag(projectItemId, tagId);
            Log.create("[Control] : Remove Tag success (" + rows + " row(s))");
            return rows;
        } catch (DatabaseException e) {
            Log.err("[Control] : Remove Tag failed: " + e.getMessage());
            throw e;
        }
    }

    public List<Tag> getTags(Integer projectItemId) throws DatabaseException {
        Log.create("[Control] : Get Tags for Project Item");

        if (projectItemId == null) {
            Log.err("[Control] : Get Tags failed - projectItemId is null");
            return new ArrayList<>();
        }

        try {
            List<Tag> tags = projectItemTagDAO.getTags(projectItemId);
            Log.create("[Control] : Get Tags success (" + tags.size() + " tag(s))");
            return tags;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Tags failed: " + e.getMessage());
            throw e;
        }
    }

    public List<ProjectItem> getTaggedItems(Integer tagId) throws DatabaseException {
        Log.create("[Control] : Get Tagged Items for Tag");

        if (tagId == null) {
            Log.err("[Control] : Get Tagged Items failed - tagId is null");
            return new ArrayList<>();
        }

        try {
            List<ProjectItem> items = projectItemTagDAO.getTaggedItems(tagId);
            Log.create("[Control] : Get Tagged Items success (" + items.size() + " item(s))");
            return items;
        } catch (DatabaseException e) {
            Log.err("[Control] : Get Tagged Items failed: " + e.getMessage());
            throw e;
        }
    }
}
