package dao;

/**
 * Simple smoke test runner for all DAO classes.
 *
 * Run this class directly to verify that every DAO can be instantiated.
 * It does not require JUnit or any external test dependency.
 */
public class DaoSmokeTest {

    public static void main(String[] args) throws Exception {
        check("dao.AttachmentDAO");
        check("dao.EventDAO");
        check("dao.ProjectDAO");
        check("dao.ProjectItemAsigneeDAO");
        check("dao.ProjectItemTagDAO");
        check("dao.ProjectMemberDAO");
        check("dao.SessionDAO");
        check("dao.SocialDAO");
        check("dao.TagDAO");
        check("dao.TaskDAO");
        check("dao.UserDAO");

        System.out.println("All DAO smoke tests passed.");
    }

    private static void check(String className) throws Exception {
        Object instance = Class.forName(className)
                .getDeclaredConstructor()
                .newInstance();

        if (instance == null) {
            throw new AssertionError("Failed to instantiate " + className);
        }

        System.out.println("OK: " + className);
    }
}
