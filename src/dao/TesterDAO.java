package dao;

import java.util.List;
import model.*;
import model.enums.*;

/**
 *
 * @author Silvanus
 */

/**
 *
 * (29/5)
 * Ini kelas Test untuk DAO
 * Nanti setiap DAO bakal ada method CRUD masing"
 * - Widi
 * 
 */
 
public class TesterDAO {
    
    /**
     * USER DAO
     */
    public static void testUserCreate() {
        try {
            UserDAO dao = new UserDAO();

            User user = new User();
            user.setUsername("tes6767");
            user.setEmail("tes4747@gmail.com");
            user.setPasswordHash("kolak");
            user.setFullName("Kolak Durian");
            user.setBio("Hi manis");
            user.setProfilePicture("profile.jpg");

            dao.add(user);

            System.out.println("CREATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testUserReadAll() {
        try {
            UserDAO dao = new UserDAO();

            List<User> users = dao.fetchAll();

            System.out.println("Jumlah user: " + users.size());

            for (User user : users) {
                System.out.println(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testUserUpdate() {
        try {
            UserDAO dao = new UserDAO();

            User user = dao.get(9);

            user.setUsername("UPDATE_TEST");

            dao.update(user);

            System.out.println("UPDATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testUserDelete(int id) {
        try {
            UserDAO dao = new UserDAO();

            dao.delete(id);

            System.out.println("DELETE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * SocialDAO
     */
    
    public static void testSocialCreate() {
        try {
            UserDAO userDAO = new UserDAO();
            SocialDAO socialDAO = new SocialDAO();

            User user = userDAO.get(13); // pastikan user id 1 ada

            SocialLink social = new SocialLink(
                    SocialPlatform.GITHUB,
                    "https://github.com/testuser"
            );

            social.setUser(user);

            socialDAO.add(social);

            System.out.println("CREATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testSocialReadAll() {
        try {
            SocialDAO dao = new SocialDAO();

            List<SocialLink> socials = dao.fetchAll();

            System.out.println("Jumlah social link: " + socials.size());

            for (SocialLink social : socials) {
                System.out.println(social);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testSocialUpdate(int id) {
        try {
            SocialDAO dao = new SocialDAO();

            SocialLink social = dao.get(id);

            social.setPlatform(SocialPlatform.LINKEDIN);
            social.setUrl("https://linkedin.com/in/update-test");

            dao.update(social);

            System.out.println("UPDATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testSocialDelete(int id) {
        try {
            SocialDAO dao = new SocialDAO();

            dao.delete(id);

            System.out.println("DELETE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ProjectDAO
     */
    
    public static void testProjectCreate() {
        try {
            ProjectDAO dao = new ProjectDAO();

            Project project = new Project();
            project.setName("TUBES PBO");
            project.setDescription("Project manajemen tugas kelompok");
            project.setColor("#2196F3");

            dao.add(project);

            System.out.println("CREATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testProjectReadAll() {
        try {
            ProjectDAO dao = new ProjectDAO();

            List<Project> projects = dao.fetchAll();

            System.out.println("Jumlah project: " + projects.size());

            for (Project project : projects) {
                System.out.println(project);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testProjectReadById(int id) {
        try {
            ProjectDAO dao = new ProjectDAO();

            Project project = dao.get(id);

            if (project == null) {
                System.out.println("Project tidak ditemukan");
                return;
            }

            System.out.println(project);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testProjectUpdate() {
        try {
            ProjectDAO dao = new ProjectDAO();

            Project project = dao.get(1);

            if (project == null) {
                System.out.println("Project tidak ditemukan");
                return;
            }

            project.setName("UPDATE PROJECT");
            project.setDescription("Deskripsi setelah update");
            project.setColor("#FF5722");

            dao.update(project);

            System.out.println("UPDATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testProjectDelete(int id) {
        try {
            ProjectDAO dao = new ProjectDAO();

            dao.delete(id);

            System.out.println("DELETE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * TaskDAO
     */
    
    public static void testTaskCreate(int idProject, int idUser) {
        try {
            TaskDAO dao = new TaskDAO();

            Task task = new Task();

            task.setTitle("Implement DAO");
            task.setDescription("Mengerjakan DAO Task");
            task.setColor("#2196F3");

            Project project = new Project();
            project.setId(idProject);
            task.setProject(project);

            User user = new User();
            user.setId(idUser);

            task.setCreatedBy(user);
            task.setUpdatedBy(user);

            task.setPriority(TaskPriority.HIGH);
            task.setStatus(TaskStatus.PENDING);

            dao.add(task);

            System.out.println("CREATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testTaskReadAll() {
        try {
            TaskDAO dao = new TaskDAO();

            List<Task> tasks = dao.fetchAll();

            System.out.println("Jumlah task: " + tasks.size());

            for (Task task : tasks) {
                System.out.println(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testTaskReadById(int id) {
        try {
            TaskDAO dao = new TaskDAO();

            Task task = dao.get(id);

            if (task == null) {
                System.out.println("Task tidak ditemukan");
                return;
            }

            System.out.println(task);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testTaskUpdate(int id) {
        try {
            TaskDAO dao = new TaskDAO();

            Task task = dao.get(id);

            if (task == null) {
                System.out.println("Task tidak ditemukan");
                return;
            }

            task.setTitle("UPDATE TASK");
            task.setDescription("Deskripsi setelah update");
            task.setStatus(TaskStatus.IN_PROGRESS);
            task.setPriority(TaskPriority.MEDIUM);
            
            dao.update(task);

            System.out.println("UPDATE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testTaskDelete(int id) {
        try {
            TaskDAO dao = new TaskDAO();

            dao.delete(id);

            System.out.println("DELETE SELESAI");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
