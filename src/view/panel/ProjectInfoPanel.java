/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.panel;

import control.ProjectControl;
import exception.database.DatabaseException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.List;
import model.Project;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import control.ProjectControl;
import control.SessionControl;
import utility.event.ProjectEventBus;

/**
 *
 * @author Silvanus
 */
public class ProjectInfoPanel extends javax.swing.JFrame {

    private Project project;
    private ProjectControl projectControl;

    /**
     * Creates new form ProjectInfoPanel
     */
    public ProjectInfoPanel() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        SessionControl sc = new SessionControl();
        projectControl = new ProjectControl(sc.getCurrentUser());
        this.project = projectControl.getSelected();
        loadProjectData();
    }

    public ProjectInfoPanel(Project p) {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.project = p;
        SessionControl sc = new SessionControl();
        projectControl = new ProjectControl(sc.getCurrentUser());
        
        loadProjectData();
    }

    private void loadProjectData() {
        if (project == null) {
            return;
        }

        //untuk show nama & deskripsi Project
        ProjectNameLabel.setText(project.getName());
        DescriptionTextAre.setText(project.getDescription());

        //untuk show Created At
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        CreatedAtDateLabel.setText(formatter.format(project.getCreatedAt()));
    }

    private void showProjectMenu() {

        JPopupMenu menu = new JPopupMenu();

        JMenuItem editProject = new JMenuItem("Edit Project");
        JMenuItem manageMember = new JMenuItem("Manage Members");
        JMenuItem deleteProject = new JMenuItem("Delete Project");

        editProject.addActionListener(e -> editProject());
        manageMember.addActionListener(e -> manageMembers());
        deleteProject.addActionListener(e -> deleteProject());

        menu.add(editProject);
        menu.add(manageMember);
        menu.addSeparator();
        menu.add(deleteProject);

        menu.show(MoreIconLabel, 0, MoreIconLabel.getHeight());
    }

    private void editProject() {
        javax.swing.JTextField nameField = new javax.swing.JTextField(project.getName());
        javax.swing.JTextArea descField = new javax.swing.JTextArea(
                project.getDescription() != null ? project.getDescription() : "", 4, 20);
        descField.setLineWrap(true);
        descField.setWrapStyleWord(true);
        javax.swing.JScrollPane descScroll = new javax.swing.JScrollPane(descField);

        Object[] fields = {
            "Project Name:", nameField,
            "Description:", descScroll
        };

        int result = JOptionPane.showConfirmDialog(
                this, fields, "Edit Project", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String newName = nameField.getText().trim();
            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Project name tidak boleh kosong!");
                return;
            }
            project.setName(newName);
            project.setDescription(descField.getText().trim());
            project.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
            try {
                projectControl.update(project);
                loadProjectData();
                ProjectEventBus.getInstance().notifyProjectUpdated(project);
                JOptionPane.showMessageDialog(this, "Project berhasil diperbarui!");
            } catch (exception.database.DatabaseException e) {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui project: " + e.getMessage());
            }
        }
    }

    private void manageMembers() {
        JOptionPane.showMessageDialog(this, "Manage Members");
    }

    private void deleteProject() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus project \"" + project.getName() + "\"?",
                "Hapus Project",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                projectControl.delete(project.getId());
                ProjectEventBus.getInstance().notifyProjectDeleted(project.getId());
                JOptionPane.showMessageDialog(this, "Project berhasil dihapus!");
                dispose();
            } catch (exception.database.DatabaseException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus project: " + e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProjectInfoPanel = new javax.swing.JPanel();
        TopPanel = new javax.swing.JPanel();
        ProjectIconLabel = new javax.swing.JLabel();
        ProjectNameLabel = new javax.swing.JLabel();
        MoreIconLabel = new javax.swing.JLabel();
        CancelIconLabel = new javax.swing.JLabel();
        DescriptionPanel = new javax.swing.JPanel();
        DescriptionScrollPane = new javax.swing.JScrollPane();
        DescriptionTextAre = new javax.swing.JTextArea();
        CreatedAtPanel = new javax.swing.JPanel();
        CreatedAtLabel = new javax.swing.JLabel();
        CreatedAtDateLabel = new javax.swing.JLabel();
        OwnerPanel = new javax.swing.JPanel();
        OwnerLabel = new javax.swing.JLabel();
        OwnerProfilePictureLabel = new javax.swing.JLabel();
        MemberPanel = new javax.swing.JPanel();
        MemberLabel = new javax.swing.JLabel();
        MemberAvatarPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ProjectInfoPanel.setBackground(new java.awt.Color(255, 255, 255));

        TopPanel.setBackground(new java.awt.Color(255, 255, 255));

        ProjectIconLabel.setForeground(new java.awt.Color(255, 0, 0));
        ProjectIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProjectIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/project_panel/file_icon.png"))); // NOI18N
        ProjectIconLabel.setToolTipText("");

        ProjectNameLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        ProjectNameLabel.setText("Project_Name");

        MoreIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MoreIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/project_panel/menu_icon.png"))); // NOI18N
        MoreIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MoreIconLabelMouseClicked(evt);
            }
        });

        CancelIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CancelIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/project_panel/x_icon.png"))); // NOI18N
        CancelIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CancelIconLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ProjectIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ProjectNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MoreIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CancelIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ProjectIconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProjectNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MoreIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        DescriptionPanel.setBackground(new java.awt.Color(255, 255, 255));
        DescriptionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DescriptionPanelMouseClicked(evt);
            }
        });

        DescriptionTextAre.setColumns(20);
        DescriptionTextAre.setRows(5);
        DescriptionTextAre.setText("yeah");
        DescriptionScrollPane.setViewportView(DescriptionTextAre);

        javax.swing.GroupLayout DescriptionPanelLayout = new javax.swing.GroupLayout(DescriptionPanel);
        DescriptionPanel.setLayout(DescriptionPanelLayout);
        DescriptionPanelLayout.setHorizontalGroup(
            DescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DescriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DescriptionScrollPane)
                .addContainerGap())
        );
        DescriptionPanelLayout.setVerticalGroup(
            DescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DescriptionPanelLayout.createSequentialGroup()
                .addComponent(DescriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        CreatedAtPanel.setBackground(new java.awt.Color(255, 255, 255));

        CreatedAtLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CreatedAtLabel.setText("Created At");

        CreatedAtDateLabel.setText("[DATE]");

        javax.swing.GroupLayout CreatedAtPanelLayout = new javax.swing.GroupLayout(CreatedAtPanel);
        CreatedAtPanel.setLayout(CreatedAtPanelLayout);
        CreatedAtPanelLayout.setHorizontalGroup(
            CreatedAtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreatedAtPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CreatedAtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CreatedAtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreatedAtDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        CreatedAtPanelLayout.setVerticalGroup(
            CreatedAtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreatedAtPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CreatedAtLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CreatedAtDateLabel)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        OwnerPanel.setBackground(new java.awt.Color(255, 255, 255));

        OwnerLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        OwnerLabel.setText("Owner");

        OwnerProfilePictureLabel.setText("profile_pic");
        OwnerProfilePictureLabel.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout OwnerPanelLayout = new javax.swing.GroupLayout(OwnerPanel);
        OwnerPanel.setLayout(OwnerPanelLayout);
        OwnerPanelLayout.setHorizontalGroup(
            OwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OwnerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OwnerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OwnerProfilePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OwnerPanelLayout.setVerticalGroup(
            OwnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OwnerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OwnerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OwnerProfilePictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MemberPanel.setBackground(new java.awt.Color(255, 255, 255));

        MemberLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        MemberLabel.setText("Member");

        MemberAvatarPanel.setBackground(new java.awt.Color(255, 255, 255));
        MemberAvatarPanel.setOpaque(false);

        javax.swing.GroupLayout MemberPanelLayout = new javax.swing.GroupLayout(MemberPanel);
        MemberPanel.setLayout(MemberPanelLayout);
        MemberPanelLayout.setHorizontalGroup(
            MemberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MemberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MemberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MemberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MemberAvatarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        MemberPanelLayout.setVerticalGroup(
            MemberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MemberPanelLayout.createSequentialGroup()
                .addComponent(MemberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MemberAvatarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ProjectInfoPanelLayout = new javax.swing.GroupLayout(ProjectInfoPanel);
        ProjectInfoPanel.setLayout(ProjectInfoPanelLayout);
        ProjectInfoPanelLayout.setHorizontalGroup(
            ProjectInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProjectInfoPanelLayout.createSequentialGroup()
                .addGroup(ProjectInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DescriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ProjectInfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ProjectInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(ProjectInfoPanelLayout.createSequentialGroup()
                                .addGroup(ProjectInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CreatedAtPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(OwnerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(ProjectInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MemberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProjectInfoPanelLayout.setVerticalGroup(
            ProjectInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProjectInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreatedAtPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OwnerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MemberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProjectInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProjectInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DescriptionPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DescriptionPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DescriptionPanelMouseClicked

    private void MoreIconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoreIconLabelMouseClicked
        showProjectMenu();
    }//GEN-LAST:event_MoreIconLabelMouseClicked

    private void CancelIconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelIconLabelMouseClicked

        /*
            ini kan masih window, jadi pake dispose()
            kalo udh imnpelentasi jadi panel pop up di dashboard, keknya pake setVisible(false)
            - widi (16/6)
         */
        dispose();
    }//GEN-LAST:event_CancelIconLabelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProjectInfoPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectInfoPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectInfoPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectInfoPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectInfoPanel(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CancelIconLabel;
    private javax.swing.JLabel CreatedAtDateLabel;
    private javax.swing.JLabel CreatedAtLabel;
    private javax.swing.JPanel CreatedAtPanel;
    private javax.swing.JPanel DescriptionPanel;
    private javax.swing.JScrollPane DescriptionScrollPane;
    private javax.swing.JTextArea DescriptionTextAre;
    private javax.swing.JLabel MemberLabel;
    private javax.swing.JPanel MemberPanel;
    private javax.swing.JPanel MemberAvatarPanel;
    private javax.swing.JLabel MoreIconLabel;
    private javax.swing.JLabel OwnerLabel;
    private javax.swing.JPanel OwnerPanel;
    private javax.swing.JLabel OwnerProfilePictureLabel;
    private javax.swing.JLabel ProjectIconLabel;
    private javax.swing.JPanel ProjectInfoPanel;
    private javax.swing.JLabel ProjectNameLabel;
    private javax.swing.JPanel TopPanel;
    // End of variables declaration//GEN-END:variables
}
