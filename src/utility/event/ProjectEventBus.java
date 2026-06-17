package utility.event;

import interfaces.IProjectObserver;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import utility.security.Log;

/**
 * Singleton event bus for project change notifications (Observer Pattern).
 *
 * <p><b>How it works:</b>
 * <ol>
 *   <li>Views (Observers) call {@link #subscribe} in their constructor to register.</li>
 *   <li>Views call {@link #unsubscribe} when they are removed from the screen (e.g. in
 *       {@code removeNotify()}) to avoid stale references.</li>
 *   <li>Controls or panels call {@link #notifyProjectAdded}, {@link #notifyProjectUpdated},
 *       or {@link #notifyProjectDeleted} after a successful DB operation.</li>
 *   <li>Every registered observer's corresponding {@code onProject*} method is called
 *       automatically on whatever thread the notification was triggered from —
 *       observers should wrap UI work in {@code SwingUtilities.invokeLater}.</li>
 * </ol>
 *
 * @author Observer Pattern implementation
 */
public class ProjectEventBus {

    private static final ProjectEventBus INSTANCE = new ProjectEventBus();

    // Defensive copy on notify prevents ConcurrentModificationException
    private final List<IProjectObserver> observers = new ArrayList<>();

    private ProjectEventBus() {}

    /**
     * Returns the single shared instance.
     */
    public static ProjectEventBus getInstance() {
        return INSTANCE;
    }

    /**
     * Registers an observer. Safe to call multiple times — duplicates are ignored.
     */
    public void subscribe(IProjectObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            Log.create("[EventBus] Subscribed: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * Removes an observer so it stops receiving notifications.
     */
    public void unsubscribe(IProjectObserver observer) {
        if (observers.remove(observer)) {
            Log.create("[EventBus] Unsubscribed: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * Notifies all observers that a project was added.
     *
     * @param project the newly inserted Project (with ID set)
     */
    public void notifyProjectAdded(Project project) {
        Log.create("[EventBus] notifyProjectAdded: " + (project != null ? project.getName() : "null"));
        for (IProjectObserver o : new ArrayList<>(observers)) {
            o.onProjectAdded(project);
        }
    }

    /**
     * Notifies all observers that a project was updated.
     *
     * @param project the updated Project
     */
    public void notifyProjectUpdated(Project project) {
        Log.create("[EventBus] notifyProjectUpdated: " + (project != null ? project.getName() : "null"));
        for (IProjectObserver o : new ArrayList<>(observers)) {
            o.onProjectUpdated(project);
        }
    }

    /**
     * Notifies all observers that a project was deleted.
     *
     * @param projectId the ID of the deleted project
     */
    public void notifyProjectDeleted(int projectId) {
        Log.create("[EventBus] notifyProjectDeleted id=" + projectId);
        for (IProjectObserver o : new ArrayList<>(observers)) {
            o.onProjectDeleted(projectId);
        }
    }
}
