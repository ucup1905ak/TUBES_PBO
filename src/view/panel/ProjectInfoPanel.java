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
import model.User;

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
    }

    public ProjectInfoPanel(ProjectControl projectControl, Project project) {
        this();
        this.projectControl = projectControl;
        this.project = project;

        if (this.projectControl != null && this.project != null) {
            this.projectControl.setSelected(this.project);
        }

        loadProjectData(project);
    }
    
    //Constructor untuk test koneksi database
    public ProjectInfoPanel(int projectId) {
        initComponents();

        try {
            projectControl = new ProjectControl();

            List<Project> projects = projectControl.fetchAll();

            System.out.println("Jumlah project: " + projects.size());

            if (!projects.isEmpty()) {
                
                Project project = projects.get(projectId);
//
//                ProjectNameLabel.setText(project.getName());
//                DescriptionTextAre.setText(project.getDescription());
//                CreatedAtDateLabel.setText(project.getCreatedAt() != null
//                    ? project.getCreatedAt().toString()
//                    : "[DATE]"); // Tgl dibuat
//
//                System.out.println("Project berhasil dimuat");
//                System.out.println(project.getName());
                
                projectControl.setSelected(project);
                loadProjectData(project);
                
                User owner = projectControl.getOwner();
                System.out.println("Owner: " + owner.getFullName());
                System.out.println("Profile path: " + owner.getProfilePicture());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadProjectData(Project project) {
        Project currentProject = project;

        if (currentProject == null && projectControl != null) {
            try {
                currentProject = projectControl.getProject();
            } catch (DatabaseException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
        }

        if (currentProject == null) return;

        try {
            this.project = currentProject;

            ProjectNameLabel.setText(currentProject.getName()); // Nama project
            DescriptionTextAre.setText(currentProject.getDescription()); // Deskripsi project
            CreatedAtDateLabel.setText(currentProject.getCreatedAt() != null
                    ? currentProject.getCreatedAt().toString()
                    : "[DATE]"); // Tgl dibuat
            
            if (projectControl != null) {
                // Profil pic Owner
                User owner = projectControl.getOwner();
                setOwnerProfilePicture(owner);
                loadMemberData();
            } else {
                setOwnerProfilePicture(null);
                renderMemberAvatars(null);
            }

            try {
                String hexColor = currentProject.getColor();
                if (hexColor != null && !hexColor.isBlank()) {
                    ProjectNameLabel.setForeground(Color.decode(hexColor));
                }
            } catch (Exception e) {
                ProjectNameLabel.setForeground(Color.BLACK);
            }
            
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
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
        JOptionPane.showMessageDialog(this, "Edit Project");
    }

    private void manageMembers() {
        JOptionPane.showMessageDialog(this, "Manage Members");
    }

    private void deleteProject() {
        JOptionPane.showMessageDialog(this, "Delete Project");
    }

    private void loadMemberData() {
        if (projectControl == null) {
            renderMemberAvatars(null);
            return;
        }

        try {
            List<User> members = projectControl.getMembers();
            renderMemberAvatars(members);
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void renderMemberAvatars(List<User> members) {
        MemberAvatarPanel.removeAll();
        MemberAvatarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));

        if (members != null) {
            for (User member : members) {
                MemberAvatarPanel.add(createMemberAvatarLabel(member));
            }
        }

        MemberAvatarPanel.add(createAddMemberLabel());
        MemberAvatarPanel.revalidate();
        MemberAvatarPanel.repaint();
    }

    private JLabel createMemberAvatarLabel(User member) {
        JLabel avatarLabel = new JLabel();
        avatarLabel.setPreferredSize(new java.awt.Dimension(50, 50));
        avatarLabel.setMinimumSize(new java.awt.Dimension(50, 50));
        avatarLabel.setMaximumSize(new java.awt.Dimension(50, 50));
        avatarLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        if (member == null) {
            avatarLabel.setIcon(new ImageIcon(createNeutralAvatarIcon(50, 50)));
            return avatarLabel;
        }

        String path = member.getProfilePicture();
        if (path == null || path.isBlank()) {
            avatarLabel.setIcon(new ImageIcon(createNeutralAvatarIcon(50, 50)));
            avatarLabel.setToolTipText(member.getFullName());
            return avatarLabel;
        }

        ImageIcon icon = new ImageIcon(path);
        Image circularImage = createCircularImage(icon.getImage(), 50, 50);
        avatarLabel.setIcon(new ImageIcon(circularImage));
        avatarLabel.setToolTipText(member.getFullName());
        return avatarLabel;
    }

    private JLabel createAddMemberLabel() {
        JLabel addLabel = new JLabel(new ImageIcon(createAddMemberIcon(50, 50)));
        addLabel.setPreferredSize(new java.awt.Dimension(50, 50));
        addLabel.setMinimumSize(new java.awt.Dimension(50, 50));
        addLabel.setMaximumSize(new java.awt.Dimension(50, 50));
        addLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addLabel.setToolTipText("Tambah member");
        addLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openMemberEditorPlaceholder();
            }
        });
        return addLabel;
    }

    private void openMemberEditorPlaceholder() {
        // buka panel edit member ketika panel edit member sudah tersedia.
        JOptionPane.showMessageDialog(this, "Panel edit member belum tersedia.");
    }
    
    //Tampil profile Owner
    private void setOwnerProfilePicture(User owner) {
        if (owner == null) {
            System.out.println("NULL 1");
            OwnerProfilePictureLabel.setIcon(null);
            OwnerProfilePictureLabel.setText("profile_pic");
            return;
        }

        String path = owner.getProfilePicture();
        if (path == null || path.isBlank()) {
            System.out.println("NULL 2");
            OwnerProfilePictureLabel.setIcon(null);
            OwnerProfilePictureLabel.setText("profile_pic");
            return;
        }

        ImageIcon icon = new ImageIcon(path);
        Image circularImage = createCircularImage(icon.getImage(), 50, 50);

        OwnerProfilePictureLabel.setIcon(new ImageIcon(circularImage));
        OwnerProfilePictureLabel.setText("");

        System.out.println("Loading image: " + path);
    }

    private Image createCircularImage(Image sourceImage, int width, int height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        try {
            graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setClip(new Ellipse2D.Double(0, 0, width, height));
            graphics.drawImage(sourceImage, 0, 0, width, height, null);
        } finally {
            graphics.dispose();
        }
        return bufferedImage;
    }

    private BufferedImage createNeutralAvatarIcon(int width, int height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        try {
            graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(new Color(229, 231, 235));
            graphics.fill(new Ellipse2D.Double(0, 0, width, height));
            graphics.setColor(new Color(148, 163, 184));
            graphics.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
            String text = "?";
            java.awt.FontMetrics metrics = graphics.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getAscent();
            graphics.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 4);
        } finally {
            graphics.dispose();
        }
        return bufferedImage;
    }

    private BufferedImage createAddMemberIcon(int width, int height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        try {
            graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(new Color(229, 231, 235));
            graphics.fill(new Ellipse2D.Double(0, 0, width, height));
            graphics.setStroke(new java.awt.BasicStroke(3f, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
            graphics.setColor(new Color(55, 65, 81));
            int centerX = width / 2;
            int centerY = height / 2;
            int padding = 14;
            graphics.drawLine(centerX - padding, centerY, centerX + padding, centerY);
            graphics.drawLine(centerX, centerY - padding, centerX, centerY + padding);
        } finally {
            graphics.dispose();
        }
        return bufferedImage;
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
