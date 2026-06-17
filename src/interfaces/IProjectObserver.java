package interfaces;

import model.Project;

/**
 * 
 * Ini Dipake ketika sebuah kelas perlu menampilkan data project yang dapat berubah.
 * Cara Pakainya:
 * 1. Kelas yang ingin menerima update project mengimplementasikan IProjectObserver.
 * 2. Di constructor kelas tersebut, panggil ProjectEventBus.getInstance().subscribe(this) untuk mulai menerima update.
 * 3. Di method onProjectAdded, onProjectUpdated, dan onProjectDeleted, update tampilan sesuai kebutuhan.
 * 
 * 
 * @author Farelino Alexander Kim - 240713000
 */
public interface IProjectObserver {

    /**
     * Called when a new project has been successfully added to the database.
     *
     * @param project the newly created Project with its generated ID populated
     */
    void onProjectCreated(Project project);
    
    void onProjectAdded(Project project);

    /**
     * Called when an existing project has been successfully updated.
     *
     * @param project the updated Project
     */
    void onProjectUpdated(Project project);

    /**
     * Called when a project has been successfully deleted.
     *
     * @param projectId the ID of the deleted project
     */
    void onProjectDeleted(int projectId);
}
