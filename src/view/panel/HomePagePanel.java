/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panel;

import control.ProjectControl;
import control.SessionControl;
import interfaces.IProjectObserver;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Project;
import utility.event.ProjectEventBus;

/**
 *
 * @author aldio
 */
import model.Session;
import java.io.File;
import java.awt.Image;
import javax.swing.ImageIcon;
import model.User;
import model.enums.TaskStatus;

public class HomePagePanel extends javax.swing.JPanel implements IProjectObserver {

    private SessionControl sessionControl = new SessionControl();
    private ProjectControl projectControl = new ProjectControl(sessionControl.getCurrentUser());
    private List<Project> projects = null;
    private int selectedProjectId = -1;
    private view.panel.TabelPanel tabelPanel;
    /**
     * Creates new form HomePagePanel
     */
    private onProfileClickListener profileClickListener;

    public HomePagePanel() {
//        System.out.println("MASUK");
        initComponents();

        initTabelPanel();

        try {
            projects = projectControl.fetchUserProjects(sessionControl.getCurrentUser());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ContentPanel, e.getMessage());
        }
        setupFocusClearance();
        addProjects();
        updateProfileIcon(sessionControl.getCurrentUser());
        initKanbanBoard();
    }

    public void updateProfileIcon(User currentUser) {
        if (currentUser != null && currentUser.getProfilePicture() != null && !currentUser.getProfilePicture().isEmpty()) {
            File file = new File(currentUser.getProfilePicture());
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());

                icon.getImage().flush();

                Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                Profile.setIcon(new ImageIcon(img));
                Profile.setText("");

                Profile.revalidate();
                Profile.repaint();
            } else {
                System.out.println("File foto profil tidak ditemukan di: " + file.getAbsolutePath());
            }
        }
    }

    private void addProjects() {
        projectPanel.removeAll();
        if (projects == null || projects.isEmpty()) {
            projectPanel.add(new JLabel("no project for this user."));
            return;
        }
        for (Project p : projects) {
            projectPanel.add(createProjectTab(p));
        }

        try {
            Project initial = projectControl.resolveSelectedProject();
            if (initial != null) {
                setActiveProject(initial.getId(), true);
            } else {
                setActiveProject(projects.get(0).getId(), true);
            }
        } catch (Exception e) {
            setActiveProject(projects.get(0).getId(), true);
        }
    }

    private ProjectTab createProjectTab(Project project) {
        ProjectTab tab = new ProjectTab(project);
        tab.setOnSelectListener((p) -> setActiveProject(p.getId(), true));
        return tab;
    }

    private void setActiveProject(int projectId, boolean refreshKanban) {
        this.selectedProjectId = projectId;

        Project selectedProject = null;
        if (projects != null) {
            for (Project p : projects) {
                if (p.getId() == projectId) {
                    selectedProject = p;
                    break;
                }
            }
        }

        if (selectedProject != null) {
            projectControl.setSelected(selectedProject);

            if (this.tabelPanel != null) {
                this.tabelPanel.setProjectContext(selectedProject.getId(), selectedProject.getName());
            }
        }

        for (java.awt.Component c : projectPanel.getComponents()) {
            if (c instanceof ProjectTab) {
                ProjectTab tab = (ProjectTab) c;
                if (tab.getProjectId() == projectId) {
                    tab.setSelectedState(true); 
                } else {
                    tab.setSelectedState(false); 
                }
            }
        }
    }

    private void initKanbanBoard() {

        kanbanArea.add(new KanbanPanel(TaskStatus.PENDING));
        kanbanArea.add(new KanbanPanel(TaskStatus.IN_PROGRESS));
        kanbanArea.add(new KanbanPanel(TaskStatus.DONE));

        kanbanArea.revalidate();
        kanbanArea.repaint();
    }

    public void setOnProfileClickListener(onProfileClickListener listener) {
        this.profileClickListener = listener;
    }

    public interface onProfileClickListener {

        void onProfileClick();
    }

    private void setupFocusClearance() {
        MainPanel.setFocusable(true);
        TopBarPanel.setFocusable(true);
        SideBarPanel.setFocusable(true);
        ContentPanel.setFocusable(true);

        java.awt.event.MouseAdapter clearFocusAdapter = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MainPanel.requestFocusInWindow();
            }
        };

        this.addMouseListener(clearFocusAdapter);
        MainPanel.addMouseListener(clearFocusAdapter);
        TopBarPanel.addMouseListener(clearFocusAdapter);
        SideBarPanel.addMouseListener(clearFocusAdapter);
        ContentPanel.addMouseListener(clearFocusAdapter);
    }

    private void initTabelPanel() {
//        Tabel.removeAll();
//
//        Tabel.setLayout(new java.awt.BorderLayout());
//
//        TabelPanel customTabelPanel = new TabelPanel();
//
//        Tabel.add(customTabelPanel, java.awt.BorderLayout.CENTER);
//
//        Tabel.revalidate();
//        Tabel.repaint();
        this.tabelPanel = new view.panel.TabelPanel();

        Tabel.setLayout(new java.awt.BorderLayout());
        Tabel.add(this.tabelPanel, java.awt.BorderLayout.CENTER);
        Tabel.revalidate();
        Tabel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        MainPanel = new javax.swing.JPanel();
        TopBarPanel = new javax.swing.JPanel();
        SearchTxt = new javax.swing.JTextField();
        Profile = new javax.swing.JLabel();
        SideBarPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        projectPanel = new javax.swing.JPanel();
        ContentPanel = new javax.swing.JTabbedPane();
        Tabel = new javax.swing.JPanel();
        Kanban = new javax.swing.JPanel();
        AddTaskEventButton = new javax.swing.JButton();
        kanbanArea = new javax.swing.JPanel();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        MainPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        MainPanel.setVerifyInputWhenFocusTarget(false);

        TopBarPanel.setBackground(new java.awt.Color(204, 0, 51));

        SearchTxt.setText("Cari Kegiatan");
        SearchTxt.setAutoscrolls(false);
        SearchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTxtActionPerformed(evt);
            }
        });

        Profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dashboard_panel/profile.png"))); // NOI18N
        Profile.setToolTipText("");
        Profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProfileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout TopBarPanelLayout = new javax.swing.GroupLayout(TopBarPanel);
        TopBarPanel.setLayout(TopBarPanelLayout);
        TopBarPanelLayout.setHorizontalGroup(
            TopBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopBarPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SearchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(375, 375, 375)
                .addComponent(Profile)
                .addContainerGap())
        );
        TopBarPanelLayout.setVerticalGroup(
            TopBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TopBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchTxt)
                    .addGroup(TopBarPanelLayout.createSequentialGroup()
                        .addComponent(Profile)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        SideBarPanel.setBackground(new java.awt.Color(255, 255, 255));
        SideBarPanel.setMaximumSize(new java.awt.Dimension(32767, 720));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(162, 0, 33));
        jLabel2.setText("+");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(162, 0, 33));
        jLabel1.setText("Proyek");

        projectPanel.setMaximumSize(new java.awt.Dimension(500, 10000));
        projectPanel.setLayout(new javax.swing.BoxLayout(projectPanel, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout SideBarPanelLayout = new javax.swing.GroupLayout(SideBarPanel);
        SideBarPanel.setLayout(SideBarPanelLayout);
        SideBarPanelLayout.setHorizontalGroup(
            SideBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SideBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(SideBarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        SideBarPanelLayout.setVerticalGroup(
            SideBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SideBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(projectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        for(int i= 0; i < 3 ; i++){
            projectPanel.add(new JLabel());
        }

        javax.swing.GroupLayout TabelLayout = new javax.swing.GroupLayout(Tabel);
        Tabel.setLayout(TabelLayout);
        TabelLayout.setHorizontalGroup(
            TabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1078, Short.MAX_VALUE)
        );
        TabelLayout.setVerticalGroup(
            TabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );

        ContentPanel.addTab("Tabel", Tabel);

        Kanban.setLayout(new java.awt.GridBagLayout());

        AddTaskEventButton.setText("tambah");
        AddTaskEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTaskEventButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        Kanban.add(AddTaskEventButton, gridBagConstraints);

        kanbanArea.setMaximumSize(new java.awt.Dimension(750, 600));
        kanbanArea.setPreferredSize(new java.awt.Dimension(750, 600));
        kanbanArea.setLayout(new javax.swing.BoxLayout(kanbanArea, javax.swing.BoxLayout.X_AXIS));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        Kanban.add(kanbanArea, gridBagConstraints);

        ContentPanel.addTab("Kanban", Kanban);

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addComponent(SideBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContentPanel))
            .addComponent(TopBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addComponent(TopBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SideBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ContentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1553, 1553, 1553))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SearchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchTxtActionPerformed

    private void ProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileMouseClicked
        if (profileClickListener != null) {
            profileClickListener.onProfileClick();
        }
    }//GEN-LAST:event_ProfileMouseClicked

    private void AddTaskEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTaskEventButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddTaskEventButtonActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

        new AddProjectPanel().setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    @Override
    public void addNotify() {
        super.addNotify();
        ProjectEventBus.getInstance().subscribe(this);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        ProjectEventBus.getInstance().unsubscribe(this);
    }

    // ── IProjectObserver implementation ──────────────────────────────────────
    @Override
    public void onProjectAdded(Project project) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            projectPanel.add(createProjectTab(project));
            setActiveProject(project.getId(), true);
            projectPanel.revalidate();
            projectPanel.repaint();
        });
    }

    @Override
    public void onProjectUpdated(Project project) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            for (java.awt.Component c : projectPanel.getComponents()) {
                if (c instanceof ProjectTab) {
                    ProjectTab tab = (ProjectTab) c;
                    if (tab.getProjectId() == project.getId()) {
                        tab.updateProject(project);
                        break;
                    }
                }
            }
            if (project != null && project.getId() == selectedProjectId) {
                setActiveProject(project.getId(), false);
            }
        });
    }

    @Override
    public void onProjectDeleted(int projectId) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            java.awt.Component toRemove = null;
            for (java.awt.Component c : projectPanel.getComponents()) {
                if (c instanceof ProjectTab) {
                    ProjectTab tab = (ProjectTab) c;
                    if (tab.getProjectId() == projectId) {
                        toRemove = tab;
                        break;
                    }
                }
            }
            if (toRemove != null) {
                projectPanel.remove(toRemove);
                if (selectedProjectId == projectId) {
                    int firstId = -1;
                    for (java.awt.Component c : projectPanel.getComponents()) {
                        if (c instanceof ProjectTab) {
                            firstId = ((ProjectTab) c).getProjectId();
                            break;
                        }
                    }
                    if (firstId > 0) {
                        setActiveProject(firstId, true);
                    } else {
                        selectedProjectId = -1;
                    }
                }
                projectPanel.revalidate();
                projectPanel.repaint();
            }
        });
    }

    @Override
    public void onProjectCreated(Project p) {
        javax.swing.SwingUtilities.invokeLater(() -> {

            if (projects == null || projects.isEmpty()) {
                projectPanel.removeAll();
            }

            projects.add(p);

            view.panel.ProjectTab tab = new view.panel.ProjectTab(p);

            tab.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, tab.getPreferredSize().height));
            tab.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT); // Rata kiri

            tab.setOnSelectListener(project -> setActiveProject(project.getId(), true));

            projectPanel.add(tab);

            projectPanel.revalidate();
            projectPanel.repaint();

            setActiveProject(p.getId(), true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddTaskEventButton;
    private javax.swing.JTabbedPane ContentPanel;
    private javax.swing.JPanel Kanban;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel Profile;
    private javax.swing.JTextField SearchTxt;
    private javax.swing.JPanel SideBarPanel;
    private javax.swing.JPanel Tabel;
    private javax.swing.JPanel TopBarPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel kanbanArea;
    private javax.swing.JPanel projectPanel;
    // End of variables declaration//GEN-END:variables
}
