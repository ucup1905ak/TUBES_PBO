/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.panel.component;

/**
 *
 * @author aldio
 */
import control.AttachmentControl;
import control.SessionControl;
import control.TaskControl;
import dao.ProjectItemAssigneeDAO;
import exception.database.DatabaseException;
import java.awt.*;
import java.io.File;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import model.Attachment;
import model.Project;
import model.Task;
import model.User;
import model.enums.AttachmentType;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import java.util.List;
import control.UserControl;

public class CreateTask extends javax.swing.JPanel {

    /**
     * Creates new form CreateTask
     */
    private int projectId;
    private String projectName;
    private File attachedFile = null;

    // Komponen Input
    private JTextField txtTaskName;
    private JTextArea txtDescription;
    private JComboBox<TaskStatus> cbStatus;
    private JComboBox<TaskPriority> cbPriority;
    private JSpinner spinStartDate;
    private JSpinner spinDueDate;
    private JSpinner spinEndDate;
    private JComboBox<String> cbAssignee;
    private JLabel lblAttachmentStatus;

    public CreateTask(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;

//        initComponents();
        this.removeAll();
        buildCustomUI();
    }

    public CreateTask() {
        this(1, "Project Name");
    }

    private void buildCustomUI() {
        this.setLayout(new BorderLayout(0, 15));
        this.setBackground(new Color(250, 250, 252));
        this.setBorder(new EmptyBorder(25, 30, 25, 30));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        headerPanel.setBackground(new Color(250, 250, 252));
        JLabel titleProject = new JLabel(projectName != null ? projectName : "Project Name");
        titleProject.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleProject.setForeground(new Color(33, 37, 41));

        JLabel titleTask = new JLabel("Task");
        titleTask.setFont(new Font("SansSerif", Font.PLAIN, 16));
        titleTask.setForeground(new Color(108, 117, 125));

        headerPanel.add(titleProject);
        headerPanel.add(titleTask);
        this.add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(250, 250, 252));

        txtTaskName = new JTextField();
        txtTaskName.setPreferredSize(new Dimension(500, 45));
        txtTaskName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        txtTaskName.setFont(new Font("SansSerif", Font.BOLD, 24));
        txtTaskName.setForeground(Color.DARK_GRAY);
        txtTaskName.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(5, 10, 5, 10)
        ));
        txtTaskName.setText("Task Name");
        formPanel.add(txtTaskName);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        txtDescription = new JTextArea(4, 20);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDescription.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollDesc = new JScrollPane(txtDescription);
        scrollDesc.setBorder(new LineBorder(new Color(230, 230, 230), 1, true));
        scrollDesc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        formPanel.add(scrollDesc);
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel propsPanel = new JPanel(new GridBagLayout());
        propsPanel.setBackground(new Color(250, 250, 252));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 0, 12, 20);
        gbc.anchor = GridBagConstraints.WEST;
        int row = 0;

        JPanel attachPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        attachPanel.setBackground(new Color(250, 250, 252));
        JButton btnAttach = new JButton("+ Add files here");
        btnAttach.setBackground(Color.WHITE);
        btnAttach.setFocusPainted(false);
        btnAttach.setPreferredSize(new Dimension(150, 40));
        btnAttach.setBorder(BorderFactory.createDashedBorder(Color.LIGHT_GRAY, 2, 2));
        btnAttach.addActionListener(e -> chooseFile());
        lblAttachmentStatus = new JLabel("");
        lblAttachmentStatus.setBorder(new EmptyBorder(0, 10, 0, 0));
        attachPanel.add(btnAttach);
        attachPanel.add(lblAttachmentStatus);
        addFormRow(propsPanel, gbc, row++, "📎 Attachments", attachPanel);

        JPanel tagPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tagPanel.setBackground(new Color(250, 250, 252));
        JButton btnAddTag = new JButton("+ Add Tags");
        btnAddTag.setBackground(Color.WHITE);
        btnAddTag.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        btnAddTag.setPreferredSize(new Dimension(100, 25));
        tagPanel.add(btnAddTag);
        addFormRow(propsPanel, gbc, row++, "🏷️ Tags", tagPanel);

        cbStatus = new JComboBox<>(TaskStatus.values());
        cbStatus.setPreferredSize(new Dimension(150, 30));
        cbStatus.setBackground(new Color(240, 245, 255));
        addFormRow(propsPanel, gbc, row++, "✓ Status", cbStatus);

        cbPriority = new JComboBox<>(TaskPriority.values());
        cbPriority.setSelectedItem(TaskPriority.HIGH);
        cbPriority.setPreferredSize(new Dimension(150, 30));
        cbPriority.setBackground(new Color(255, 240, 240));
        addFormRow(propsPanel, gbc, row++, "⚠ Priority", cbPriority);

        JPanel datesPanel = new JPanel(new GridLayout(3, 2, 5, 10));
        datesPanel.setBackground(new Color(250, 250, 252));

        spinStartDate = createDateSpinner();
        spinDueDate = createDateSpinner();
        spinEndDate = createDateSpinner();

        datesPanel.add(new JLabel("Start"));
        datesPanel.add(spinStartDate);
        datesPanel.add(new JLabel("Due"));
        datesPanel.add(spinDueDate);
        datesPanel.add(new JLabel("End"));
        datesPanel.add(spinEndDate);
        addFormRow(propsPanel, gbc, row++, "📅 Dates", datesPanel);

        cbAssignee = new JComboBox<>(loadAssigneeModel());
        cbAssignee.setPreferredSize(new Dimension(200, 30));
        addFormRow(propsPanel, gbc, row++, "👤 Assignee", cbAssignee);

        formPanel.add(propsPanel);

        JScrollPane mainScroll = new JScrollPane(formPanel);
        mainScroll.setBorder(null);
        mainScroll.getVerticalScrollBar().setUnitIncrement(16);
        this.add(mainScroll, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(new Color(250, 250, 252));
        footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton btnCreateTask = new JButton("Create Task");
        btnCreateTask.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCreateTask.setBackground(new Color(185, 28, 28)); // Warna Merah (High Priority/Sesuai gambar)
        btnCreateTask.setForeground(Color.WHITE);
        btnCreateTask.setPreferredSize(new Dimension(140, 40));
        btnCreateTask.setFocusPainted(false);
        btnCreateTask.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCreateTask.addActionListener(e -> saveTaskToDatabase());
        footerPanel.add(btnCreateTask);

        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private DefaultComboBoxModel<String> loadAssigneeModel() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("+ Select Assignee");

        try {
            UserControl userControl = new UserControl();

            List<User> allUsers = userControl.fetchAll();

            if (allUsers != null) {
                for (User user : allUsers) {
                    model.addElement(user.getId() + " - " + user.getFullName());
                }
            }
        } catch (Exception e) {
            System.err.println("Gagal memuat daftar assignee: " + e.getMessage());
        }

        return model;
    }

    private JSpinner createDateSpinner() {
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "EEEE, dd MMM yyyy"));
        spinner.setPreferredSize(new Dimension(180, 28));
        return spinner;
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, Component inputComp) {
        // Label Kiri
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(new Color(108, 117, 125)); // Gray text
        label.setPreferredSize(new Dimension(120, 30));
        panel.add(label, gbc);

        // Input Kanan
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;
        panel.add(inputComp, gbc);
    }

    private void saveTaskToDatabase() {
        String title = txtTaskName.getText().trim();
        String description = txtDescription.getText().trim();
        TaskStatus status = (TaskStatus) cbStatus.getSelectedItem();
        TaskPriority priority = (TaskPriority) cbPriority.getSelectedItem();
        Date startDate = (Date) spinStartDate.getValue();
        Date dueDate = (Date) spinDueDate.getValue();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task Name tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int assigneeUserId = -1;
        if (cbAssignee.getSelectedIndex() > 0) {
            String selectedAssignee = (String) cbAssignee.getSelectedItem();
            assigneeUserId = Integer.parseInt(selectedAssignee.split(" - ")[0]);
        }
        try {
            Task newTask = new Task();
            newTask.setTitle(title);
            newTask.setDescription(description);

            Project project = new Project();
            project.setId(this.projectId);
            newTask.setProject(project);

            User currentUser = SessionControl.getInstance().getCurrentUser();
            if (currentUser == null) {
                currentUser = new User();
                currentUser.setId(1);
            }
            newTask.setCreatedBy(currentUser);
            newTask.setStatus(status);
            newTask.setPriority(priority);
            newTask.setStartDate(startDate);
            newTask.setDueDate(dueDate);

            TaskControl taskControl = new TaskControl();
            int generatedItemId = taskControl.add(newTask);

            if (generatedItemId > 0) {
                if (assigneeUserId != -1) {
                    ProjectItemAssigneeDAO assigneeDAO = new ProjectItemAssigneeDAO();
                    assigneeDAO.assignUser(generatedItemId, assigneeUserId);
                }

                if (attachedFile != null) {
                    Attachment attachment = new Attachment();
                    Task assignedItem = new Task();
                    assignedItem.setId(generatedItemId);
                    attachment.setProjectItem(assignedItem);
                    attachment.setFileName(attachedFile.getName());
                    attachment.setFilePath(attachedFile.getAbsolutePath());
                    attachment.setFileSize(attachedFile.length());
                    attachment.setFileType(getAttachmentType(attachedFile.getName()));

                    AttachmentControl attachmentControl = new AttachmentControl();
                    attachmentControl.add(attachment);
                }

                JOptionPane.showMessageDialog(this, "Task baru berhasil dibuat!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

                // Menutup Panel Dialog Otomatis
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) {
                    window.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Gagal membuat task.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            attachedFile = chooser.getSelectedFile();
            lblAttachmentStatus.setText(attachedFile.getName());
            lblAttachmentStatus.setForeground(new Color(25, 135, 84)); // Green success
        }
    }

    private AttachmentType getAttachmentType(String fileName) {
        String lowerName = fileName.toLowerCase();
        if (lowerName.matches(".*\\.(png|jpg|jpeg|gif|bmp|webp)$")) {
            return AttachmentType.IMAGE;
        }
        if (lowerName.matches(".*\\.(mp4|avi|mkv|mov|wmv)$")) {
            return AttachmentType.VIDEO;
        }
        if (lowerName.matches(".*\\.(zip|rar|7z|tar|gz)$")) {
            return AttachmentType.ARCHIVE;
        }
        if (lowerName.matches(".*\\.(pdf|doc|docx|xls|xlsx|ppt|pptx|txt|csv)$")) {
            return AttachmentType.DOCUMENT;
        }
        return AttachmentType.OTHER;
    }

    private void clearForm() {
        txtTaskName.setText("");
        txtDescription.setText("");
        cbStatus.setSelectedIndex(0);
        cbPriority.setSelectedIndex(0);
        spinStartDate.setValue(new Date());
        spinDueDate.setValue(new Date());
        cbAssignee.setSelectedIndex(0);
        attachedFile = null;
        lblAttachmentStatus.setText("No file attached");
        lblAttachmentStatus.setForeground(new Color(142, 146, 151));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        task = new javax.swing.JLabel();
        atachment = new javax.swing.JLabel();
        tasknamepanel = new javax.swing.JPanel();
        taskname = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descArea = new javax.swing.JTextArea();
        tags = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        priority = new javax.swing.JLabel();
        dates = new javax.swing.JLabel();
        assignee = new javax.swing.JLabel();
        createtask = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(253, 253, 253));
        jPanel1.setPreferredSize(new java.awt.Dimension(621, 752));

        title.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        title.setForeground(new java.awt.Color(29, 29, 29));
        title.setText("Project Name");

        task.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        task.setForeground(new java.awt.Color(106, 106, 106));
        task.setText("Task");

        atachment.setBackground(new java.awt.Color(20, 20, 20));
        atachment.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        atachment.setForeground(new java.awt.Color(20, 20, 20));
        atachment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/CreateTaskPanel/clip.png"))); // NOI18N
        atachment.setText("Attachment");

        taskname.setBackground(new java.awt.Color(253, 253, 253));
        taskname.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        taskname.setForeground(new java.awt.Color(201, 201, 201));
        taskname.setText("Task Name");
        taskname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(233, 233, 233), 1, true));

        javax.swing.GroupLayout tasknamepanelLayout = new javax.swing.GroupLayout(tasknamepanel);
        tasknamepanel.setLayout(tasknamepanelLayout);
        tasknamepanelLayout.setHorizontalGroup(
            tasknamepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tasknamepanelLayout.createSequentialGroup()
                .addComponent(taskname, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        tasknamepanelLayout.setVerticalGroup(
            tasknamepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        descArea.setBackground(new java.awt.Color(253, 253, 253));
        descArea.setColumns(20);
        descArea.setForeground(new java.awt.Color(131, 124, 124));
        descArea.setRows(5);
        descArea.setText("Add Description...");
        descArea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 232, 232), 1, true));
        descArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(descArea);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );

        tags.setBackground(new java.awt.Color(20, 20, 20));
        tags.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tags.setForeground(new java.awt.Color(20, 20, 20));
        tags.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tags.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/CreateTaskPanel/Tags.png"))); // NOI18N
        tags.setText("Tags");
        tags.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        tags.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        status.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        status.setForeground(new java.awt.Color(20, 20, 20));
        status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/CreateTaskPanel/crooss.png"))); // NOI18N
        status.setText("Status");

        priority.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        priority.setForeground(new java.awt.Color(20, 20, 20));
        priority.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/CreateTaskPanel/alert.png"))); // NOI18N
        priority.setText("Priority");

        dates.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        dates.setForeground(new java.awt.Color(20, 20, 20));
        dates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/CreateTaskPanel/dates.png"))); // NOI18N
        dates.setText("Dates");

        assignee.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        assignee.setForeground(new java.awt.Color(20, 20, 20));
        assignee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/CreateTaskPanel/user_icon.png"))); // NOI18N
        assignee.setText("Assignee");

        createtask.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        createtask.setText("Create Task");
        createtask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createtaskActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(task)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priority)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tasknamepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tags)
                            .addComponent(atachment)
                            .addComponent(status)
                            .addComponent(assignee)
                            .addComponent(dates))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(createtask)
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(task))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tasknamepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(atachment)
                .addGap(91, 91, 91)
                .addComponent(tags)
                .addGap(61, 61, 61)
                .addComponent(status)
                .addGap(43, 43, 43)
                .addComponent(priority)
                .addGap(39, 39, 39)
                .addComponent(dates)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(assignee)
                .addGap(48, 48, 48)
                .addComponent(createtask, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createtaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createtaskActionPerformed
        saveTaskToDatabase();
    }//GEN-LAST:event_createtaskActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel assignee;
    private javax.swing.JLabel atachment;
    private javax.swing.JButton createtask;
    private javax.swing.JLabel dates;
    private javax.swing.JTextArea descArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel priority;
    private javax.swing.JLabel status;
    private javax.swing.JLabel tags;
    private javax.swing.JLabel task;
    private javax.swing.JTextField taskname;
    private javax.swing.JPanel tasknamepanel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
