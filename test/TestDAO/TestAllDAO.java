package TestDAO;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Runnable CRUD smoke tests for all DAO classes.
 *
 * This runner uses reflection so it does not depend on the editor's compile-time
 * symbol resolution. It creates its own sample rows and exercises each DAO.
 */
public class TestAllDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pbo_tubes";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public static void main(String[] args) throws Exception {
        new TestAllDAO().runAll();
    }

    public void runAll() throws Exception {
        String suffix = String.valueOf(System.currentTimeMillis());

        Object userDao = newDao("dao.UserDAO");
        Object projectDao = newDao("dao.ProjectDAO");
        Object sessionDao = newDao("dao.SessionDAO");
        Object socialDao = newDao("dao.SocialDAO");
        Object projectMemberDao = newDao("dao.ProjectMemberDAO");
        Object taskDao = newDao("dao.TaskDAO");
        Object eventDao = newDao("dao.EventDAO");
        Object attachmentDao = newDao("dao.AttachmentDAO");
        Object projectItemAsigneeDao = newDao("dao.ProjectItemAsigneeDAO");
        Object projectItemTagDao = newDao("dao.ProjectItemTagDAO");
        Object tagDao = newDao("dao.TagDAO");

        Object user = testUserCrud(userDao, suffix);
        Object project = testProjectCrud(projectDao, suffix);
        testSessionCrud(sessionDao, user, suffix);
        testSocialCrud(socialDao, user, suffix);
        testProjectMemberCrud(projectMemberDao, project, user);

        int taskProjectItemId = insertProjectItem(project, user, "Task Item " + suffix);
        int eventProjectItemId = insertProjectItem(project, user, "Event Item " + suffix);
        int attachmentProjectItemId = insertProjectItem(project, user, "Attachment Item " + suffix);
        int taggableProjectItemId = insertProjectItem(project, user, "Taggable Item " + suffix);

        try {
            testTaskCrud(taskDao, project, taskProjectItemId);
            testEventCrud(eventDao, project, eventProjectItemId, suffix);
            testAttachmentCrud(attachmentDao, attachmentProjectItemId, user, suffix);
            testProjectItemAssigneeCrud(projectItemAsigneeDao, taggableProjectItemId, user);

            Object tag = testTagCrud(tagDao, suffix);
            testProjectItemTagCrud(projectItemTagDao, taggableProjectItemId, tag);
        } finally {
            deleteProjectItem(taggableProjectItemId);
            deleteProjectItem(attachmentProjectItemId);
            deleteProjectItem(eventProjectItemId);
            deleteProjectItem(taskProjectItemId);

            deleteById(userDao, user);
            deleteById(projectDao, project);
        }

        System.out.println("All DAO CRUD smoke tests passed.");
    }

    private Object testUserCrud(Object userDao, String suffix) throws Exception {
        Object user = newObject("model.User");
        invoke(user, "setUsername", "dao_user_" + suffix);
        invoke(user, "setEmail", "dao_user_" + suffix + "@example.com");
        invoke(user, "setPasswordHash", "hash-" + suffix);
        invoke(user, "setFullName", "DAO User " + suffix);
        invoke(user, "setBio", "bio-" + suffix);
        invoke(user, "setProfilePicture", "avatar-" + suffix + ".png");

        require((Integer) invoke(userDao, "add", user) == 1, "UserDAO.add failed");

        Object created = invoke(userDao, "getByUsername", "dao_user_" + suffix);
        require(created != null, "UserDAO.getByUsername failed");

        int id = (Integer) invoke(created, "getId");
        invoke(created, "setFullName", "DAO User Updated " + suffix);
        invoke(created, "setBio", "bio-updated-" + suffix);
        require((Integer) invoke(userDao, "update", created) == 1, "UserDAO.update failed");
        require(invoke(userDao, "get", id) != null, "UserDAO.get failed");

        return created;
    }

    private Object testProjectCrud(Object projectDao, String suffix) throws Exception {
        Object project = newObject("model.Project");
        invoke(project, "setName", "DAO Project " + suffix);
        invoke(project, "setDescription", "project-desc-" + suffix);
        invoke(project, "setColor", "#445566");

        require((Integer) invoke(projectDao, "add", project) == 1, "ProjectDAO.add failed");

        Object created = findByField(invoke(projectDao, "fetchAll"), "getName", "DAO Project " + suffix);
        require(created != null, "ProjectDAO.fetchAll lookup failed");

        int id = (Integer) invoke(created, "getId");
        invoke(created, "setDescription", "project-desc-updated-" + suffix);
        require((Integer) invoke(projectDao, "update", created) == 1, "ProjectDAO.update failed");
        require(invoke(projectDao, "get", id) != null, "ProjectDAO.get failed");

        // keep the project around for later member and project-item tests
        return created;
    }

    private void testSessionCrud(Object sessionDao, Object user, String suffix) throws Exception {
        Object session = newObject("model.Session");
        invoke(session, "setUser", user);
        invoke(session, "setToken", "token-" + suffix);
        invoke(session, "setCreatedAt", new Timestamp(System.currentTimeMillis()));
        invoke(session, "setExpiresAt", new Timestamp(System.currentTimeMillis() + 86_400_000L));
        invoke(session, "setActive", true);

        require((Integer) invoke(sessionDao, "add", session) == 1, "SessionDAO.add failed");

        Object created = invoke(sessionDao, "getByToken", "token-" + suffix);
        require(created != null, "SessionDAO.getByToken failed");

        int id = (Integer) invoke(created, "getId");
        invoke(created, "setToken", "token-updated-" + suffix);
        invoke(created, "setExpiresAt", new Timestamp(System.currentTimeMillis() + 172_800_000L));
        require((Integer) invoke(sessionDao, "update", created) == 1, "SessionDAO.update failed");

        Object updated = invoke(sessionDao, "getByToken", "token-updated-" + suffix);
        require(updated != null, "SessionDAO updated token not found");

        invoke(sessionDao, "invalidate", "token-updated-" + suffix);
        Object invalidated = invoke(sessionDao, "get", id);
        require(invalidated != null && !(Boolean) invoke(invalidated, "isActive"), "SessionDAO.invalidate failed");

        invoke(sessionDao, "delete", id);
        require(invoke(sessionDao, "get", id) == null, "SessionDAO.delete failed");
    }

    private void testSocialCrud(Object socialDao, Object user, String suffix) throws Exception {
        Object platform = enumValue("model.enums.SocialPlatform", "GITHUB");
        Object social = newObject("model.Social", user, platform, "https://github.com/dao-user-" + suffix);

        require((Integer) invoke(socialDao, "add", social) == 1, "SocialDAO.add failed");

        int userId = (Integer) invoke(user, "getId");
        Object created = findByField(invoke(socialDao, "findByUserId", userId), "getUrl", "https://github.com/dao-user-" + suffix);
        require(created != null, "SocialDAO.findByUserId failed");

        int id = (Integer) invoke(created, "getId");
        invoke(created, "setPlatform", enumValue("model.enums.SocialPlatform", "LINKEDIN"));
        invoke(created, "setUrl", "https://linkedin.com/in/dao-user-" + suffix);
        require((Integer) invoke(socialDao, "update", created) == 1, "SocialDAO.update failed");

        Object updated = invoke(socialDao, "findByUserId", userId);
        require(findByField(updated, "getUrl", "https://linkedin.com/in/dao-user-" + suffix) != null,
                "SocialDAO update not persisted");

        require((Integer) invoke(socialDao, "delete", id) == 1, "SocialDAO.delete failed");
    }

    private void testProjectMemberCrud(Object projectMemberDao, Object project, Object user) throws Exception {
        int projectId = (Integer) invoke(project, "getId");
        int userId = (Integer) invoke(user, "getId");

        require((Integer) invoke(projectMemberDao, "add", projectId, userId, enumValue("model.enums.UserRole", "TEAM_MEMBER")) == 1,
                "ProjectMemberDAO.add failed");

        require(containsId(invoke(projectMemberDao, "getUserByProject", projectId), userId),
                "ProjectMemberDAO.getUserByProject failed");
        require(containsId(invoke(projectMemberDao, "getProjectByUser", userId), projectId),
                "ProjectMemberDAO.getProjectByUser failed");
        require(enumName(invoke(projectMemberDao, "getRole", projectId, userId)).equals("TEAM_MEMBER"),
                "ProjectMemberDAO.getRole failed");

        require((Integer) invoke(projectMemberDao, "updateRole", projectId, userId,
                enumValue("model.enums.UserRole", "PROJECT_OWNER")) == 1, "ProjectMemberDAO.updateRole failed");
        require(enumName(invoke(projectMemberDao, "getRole", projectId, userId)).equals("PROJECT_OWNER"),
                "ProjectMemberDAO.updateRole not persisted");

        require((Integer) invoke(projectMemberDao, "remove", projectId, userId) == 1,
                "ProjectMemberDAO.remove failed");
    }

    private void testTaskCrud(Object taskDao, Object project, int projectItemId) throws Exception {
        Object task = newObject("model.Task");
        invoke(task, "setId", projectItemId);
        invoke(task, "setPriority", enumValue("model.enums.TaskPriority", "HIGH"));
        invoke(task, "setStatus", enumValue("model.enums.TaskStatus", "PENDING"));
        invoke(task, "setStartDate", new java.util.Date());
        invoke(task, "setDueDate", new java.util.Date(System.currentTimeMillis() + 86_400_000L));

        require((Integer) invoke(taskDao, "add", task) == 1, "TaskDAO.add failed");

        Object created = invoke(taskDao, "get", projectItemId);
        require(created != null, "TaskDAO.get failed");
        require(enumName(invoke(created, "getPriority")).equals("HIGH"), "TaskDAO add not persisted");

        invoke(created, "setPriority", enumValue("model.enums.TaskPriority", "MEDIUM"));
        invoke(created, "setStatus", enumValue("model.enums.TaskStatus", "IN_PROGRESS"));
        invoke(created, "setCompletedAt", new java.util.Date());
        require((Integer) invoke(taskDao, "update", created) == 1, "TaskDAO.update failed");

        Object updated = invoke(taskDao, "get", projectItemId);
        require(updated != null && enumName(invoke(updated, "getPriority")).equals("MEDIUM"),
                "TaskDAO update not persisted");

        require(!((List<?>) invoke(taskDao, "fetchByProject", project)).isEmpty(), "TaskDAO.fetchByProject failed");
        require(invoke(taskDao, "fetchAsignee", created) != null, "TaskDAO.fetchAsignee failed");

        require((Integer) invoke(taskDao, "delete", projectItemId) == 1, "TaskDAO.delete failed");
        require(invoke(taskDao, "get", projectItemId) == null, "TaskDAO delete not persisted");
    }

    private void testEventCrud(Object eventDao, Object project, int projectItemId, String suffix) throws Exception {
        Object event = newObject("model.Event");
        invoke(event, "setId", projectItemId);
        invoke(event, "setLocation", "Room " + suffix);
        invoke(event, "setAllDay", false);
        invoke(event, "setStartAt", new Timestamp(System.currentTimeMillis()));
        invoke(event, "setEndAt", new Timestamp(System.currentTimeMillis() + 3_600_000L));

        require((Integer) invoke(eventDao, "add", event) == 1, "EventDAO.add failed");

        Object created = invoke(eventDao, "get", projectItemId);
        require(created != null, "EventDAO.get failed");
        require(((String) invoke(created, "getLocation")).startsWith("Room "), "EventDAO add not persisted");

        invoke(created, "setLocation", "Updated Room " + suffix);
        invoke(created, "setAllDay", true);
        require((Integer) invoke(eventDao, "update", created) == 1, "EventDAO.update failed");

        Object updated = invoke(eventDao, "get", projectItemId);
        require(updated != null && (Boolean) invoke(updated, "isAllDay"), "EventDAO update not persisted");

        require(!((List<?>) invoke(eventDao, "fetchByProject", project)).isEmpty(), "EventDAO.fetchByProject failed");
        require(invoke(eventDao, "fetchAsignee", updated) != null, "EventDAO.fetchAsignee failed");

        require((Integer) invoke(eventDao, "delete", projectItemId) == 1, "EventDAO.delete failed");
        require(invoke(eventDao, "get", projectItemId) == null, "EventDAO delete not persisted");
    }

    private void testAttachmentCrud(Object attachmentDao, int projectItemId, Object user, String suffix) throws Exception {
        Object attachment = newObject("model.Attachment");
        invoke(attachment, "setProjectItem", newProjectItem(projectItemId));
        invoke(attachment, "setFileName", "attachment-" + suffix + ".txt");
        invoke(attachment, "setFilePath", "/tmp/attachment-" + suffix + ".txt");
        invoke(attachment, "setFileType", enumValue("model.enums.AttachmentType", "DOCUMENT"));
        invoke(attachment, "setFileSize", 128L);
        invoke(attachment, "setUploadedAt", new Timestamp(System.currentTimeMillis()));
        invoke(attachment, "setUploadedBy", user);

        require((Integer) invoke(attachmentDao, "add", attachment) == 1, "AttachmentDAO.add failed");

        Object created = findByField(invoke(attachmentDao, "fetchAll"), "getFilePath", "/tmp/attachment-" + suffix + ".txt");
        require(created != null, "AttachmentDAO.fetchAll lookup failed");

        int id = (Integer) invoke(created, "getId");
        invoke(created, "setFileName", "attachment-updated-" + suffix + ".txt");
        invoke(created, "setFileSize", 256L);
        require((Integer) invoke(attachmentDao, "update", created) == 1, "AttachmentDAO.update failed");

        Object updated = invoke(attachmentDao, "get", id);
        require(updated != null && (Long) invoke(updated, "getFileSize") == 256L, "AttachmentDAO update not persisted");

        require((Integer) invoke(attachmentDao, "delete", id) == 1, "AttachmentDAO.delete failed");
        require(invoke(attachmentDao, "get", id) == null, "AttachmentDAO delete not persisted");
    }

    private void testProjectItemAssigneeCrud(Object projectItemAsigneeDao, int projectItemId, Object user) throws Exception {
        int userId = (Integer) invoke(user, "getId");

        require((Integer) invoke(projectItemAsigneeDao, "assignUser", projectItemId, userId) == 1,
                "ProjectItemAsigneeDAO.assignUser failed");
        require(containsId(invoke(projectItemAsigneeDao, "getAssignees", projectItemId), userId),
                "ProjectItemAsigneeDAO.getAssignees failed");
        require(containsId(invoke(projectItemAsigneeDao, "getAssignedItems", userId), projectItemId),
                "ProjectItemAsigneeDAO.getAssignedItems failed");
        require((Integer) invoke(projectItemAsigneeDao, "removeAssignee", projectItemId, userId) == 1,
                "ProjectItemAsigneeDAO.removeAssignee failed");
    }

    private Object testTagCrud(Object tagDao, String suffix) throws Exception {
        Object tag = newObject("model.Tag");
        invoke(tag, "setName", "Tag " + suffix);
        invoke(tag, "setColor", "#ABCDEF");
        invoke(tag, "setCreatedAt", new Timestamp(System.currentTimeMillis()));

        require((Integer) invoke(tagDao, "add", tag) == 1, "TagDAO.add failed");

        Object created = findByField(invoke(tagDao, "fetchAll"), "getName", "Tag " + suffix);
        require(created != null, "TagDAO.fetchAll lookup failed");

        int id = (Integer) invoke(created, "getId");
        invoke(created, "setColor", "#123456");
        require((Integer) invoke(tagDao, "update", created) == 1, "TagDAO.update failed");

        Object updated = invoke(tagDao, "get", id);
        require(updated != null && "#123456".equals(invoke(updated, "getColor")), "TagDAO update not persisted");

        require((Integer) invoke(tagDao, "delete", id) == 1, "TagDAO.delete failed");
        require(invoke(tagDao, "get", id) == null, "TagDAO delete not persisted");

        // recreate tag for the project-item tag relation test
        invoke(tagDao, "add", tag);
        Object recreated = findByField(invoke(tagDao, "fetchAll"), "getName", "Tag " + suffix);
        require(recreated != null, "TagDAO recreate failed");
        return recreated;
    }

    private void testProjectItemTagCrud(Object projectItemTagDao, int projectItemId, Object tag) throws Exception {
        int tagId = (Integer) invoke(tag, "getId");

        require((Integer) invoke(projectItemTagDao, "assignTag", projectItemId, tagId) == 1,
                "ProjectItemTagDAO.assignTag failed");
        require(containsId(invoke(projectItemTagDao, "getTags", projectItemId), tagId),
                "ProjectItemTagDAO.getTags failed");
        require(containsId(invoke(projectItemTagDao, "getTaggedItems", tagId), projectItemId),
                "ProjectItemTagDAO.getTaggedItems failed");
        require((Integer) invoke(projectItemTagDao, "removeTag", projectItemId, tagId) == 1,
                "ProjectItemTagDAO.removeTag failed");

        deleteTag(tagId);
    }

    private int insertProjectItem(Object project, Object user, String title) throws Exception {
        int projectId = (Integer) invoke(project, "getId");
        int userId = (Integer) invoke(user, "getId");
        long now = System.currentTimeMillis();

        try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO project_items (title, description, color, project_id, created_by, updated_by, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, title);
            ps.setString(2, title + " description");
            ps.setString(3, "#445566");
            ps.setInt(4, projectId);
            ps.setInt(5, userId);
            ps.setInt(6, userId);
            ps.setTimestamp(7, new Timestamp(now));
            ps.setTimestamp(8, new Timestamp(now));
            require(ps.executeUpdate() == 1, "project_items insert failed");

            try (ResultSet rs = ps.getGeneratedKeys()) {
                require(rs.next(), "project_items generated key missing");
                return rs.getInt(1);
            }
        }
    }

    private void deleteProjectItem(int id) {
        try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = c.prepareStatement("DELETE FROM project_items WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Skipping project_items cleanup for id=" + id + ": " + e.getMessage());
        }
    }

    private void deleteTag(int id) {
        try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = c.prepareStatement("DELETE FROM tags WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Skipping tag cleanup for id=" + id + ": " + e.getMessage());
        }
    }

    private Object newProjectItem(int id) throws Exception {
        Object item = newConcreteProjectItem();
        invoke(item, "setId", id);
        return item;
    }

    private Object newConcreteProjectItem() throws Exception {
        String pkg = "dynamicdao";
        String cls = "TestProjectItem" + System.currentTimeMillis();
        String fqcn = pkg + "." + cls;
        File dir = Files.createTempDirectory("dao-test-").toFile();
        File srcDir = new File(dir, pkg);
        if (!srcDir.mkdirs()) {
            throw new IllegalStateException("Failed to create temp source dir");
        }

        File javaFile = new File(srcDir, cls + ".java");
        try (FileWriter writer = new FileWriter(javaFile)) {
            writer.write("package " + pkg + ";\n");
            writer.write("public class " + cls + " extends model.ProjectItem {\n");
            writer.write("  public " + cls + "() { super(); }\n");
            writer.write("}\n");
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("JavaCompiler not available in this runtime");
        }

        List<String> options = new ArrayList<>();
        options.add("-classpath");
        options.add(System.getProperty("java.class.path"));
        try (StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null)) {
            boolean ok = compiler.getTask(null, fm, null, options, null, fm.getJavaFileObjects(javaFile)).call();
            if (!ok) {
                throw new IllegalStateException("Failed to compile dynamic ProjectItem helper");
            }
        }

        URLClassLoader loader = new URLClassLoader(new URL[]{dir.toURI().toURL()}, getClass().getClassLoader());
        Class<?> type = Class.forName(fqcn, true, loader);
        return type.getDeclaredConstructor().newInstance();
    }

    private Object newDao(String className) throws Exception {
        return Class.forName(className).getDeclaredConstructor().newInstance();
    }

    private Object newObject(String className, Object... args) throws Exception {
        Class<?> type = Class.forName(className);
        if (args.length == 0) {
            return type.getDeclaredConstructor().newInstance();
        }

        for (Constructor<?> ctor : type.getDeclaredConstructors()) {
            Class<?>[] paramTypes = ctor.getParameterTypes();
            if (paramTypes.length != args.length) {
                continue;
            }
            if (matches(paramTypes, args)) {
                ctor.setAccessible(true);
                return ctor.newInstance(args);
            }
        }

        throw new NoSuchMethodException("No matching constructor for " + className);
    }

    private Object enumValue(String enumClassName, String constant) throws Exception {
        Class<?> type = Class.forName(enumClassName);
        Object[] values = type.getEnumConstants();
        if (values == null) {
            throw new IllegalArgumentException(enumClassName + " is not an enum");
        }
        for (Object value : values) {
            if (((Enum<?>) value).name().equals(constant)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Enum constant not found: " + enumClassName + '.' + constant);
    }

    private Object invoke(Object target, String methodName, Object... args) throws Exception {
        Method method = findMethod(target.getClass(), methodName, args);
        method.setAccessible(true);
        try {
            return method.invoke(target, args);
        } catch (java.lang.reflect.InvocationTargetException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof Exception exception) {
                throw exception;
            }
            throw ex;
        }
    }

    private Method findMethod(Class<?> type, String methodName, Object[] args) throws NoSuchMethodException {
        for (Method method : type.getMethods()) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            if (method.getParameterCount() != args.length) {
                continue;
            }
            if (matches(method.getParameterTypes(), args)) {
                return method;
            }
        }
        throw new NoSuchMethodException(methodName + " on " + type.getName());
    }

    private boolean matches(Class<?>[] paramTypes, Object[] args) {
        for (int i = 0; i < paramTypes.length; i++) {
            if (args[i] == null) {
                continue;
            }
            if (!wrap(paramTypes[i]).isAssignableFrom(args[i].getClass())) {
                return false;
            }
        }
        return true;
    }

    private Class<?> wrap(Class<?> type) {
        if (!type.isPrimitive()) {
            return type;
        }
        if (type == int.class) return Integer.class;
        if (type == long.class) return Long.class;
        if (type == boolean.class) return Boolean.class;
        if (type == double.class) return Double.class;
        if (type == float.class) return Float.class;
        if (type == short.class) return Short.class;
        if (type == byte.class) return Byte.class;
        if (type == char.class) return Character.class;
        return type;
    }

    private Object findByField(Object collection, String getterName, Object expectedValue) throws Exception {
        if (!(collection instanceof Iterable<?> iterable)) {
            return null;
        }
        for (Object item : iterable) {
            Object value = invoke(item, getterName);
            if (expectedValue == null ? value == null : expectedValue.equals(value)) {
                return item;
            }
        }
        return null;
    }

    private boolean containsId(Object collection, int id) throws Exception {
        if (!(collection instanceof Iterable<?> iterable)) {
            return false;
        }
        for (Object item : iterable) {
            Object value = invoke(item, "getId");
            if (value instanceof Integer && ((Integer) value) == id) {
                return true;
            }
        }
        return false;
    }

    private String enumName(Object enumValue) {
        return enumValue == null ? "null" : ((Enum<?>) enumValue).name();
    }

    private void deleteById(Object dao, Object entity) {
        try {
            int id = (Integer) invoke(entity, "getId");
            Method delete = dao.getClass().getMethod("delete", Integer.class);
            delete.invoke(dao, id);
        } catch (Exception ignored) {
            // best-effort cleanup
        }
    }

    private void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
