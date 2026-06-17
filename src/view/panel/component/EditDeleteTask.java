package view.panel.component;

import control.TaskControl;
import control.UserControl;
import dao.ProjectItemAssigneeDAO;
import exception.database.DatabaseException;
import java.awt.*;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import model.Task;
import model.User;
import model.enums.TaskPriority;
import model.enums.TaskStatus;

public class EditDeleteTask extends JPanel {

    private int taskId;
    private int projectId;
    private String projectName;

    private JTextField txtTaskName;
    private JTextArea txtDescription;
    private JComboBox<TaskStatus> cbStatus;
    private JComboBox<TaskPriority> cbPriority;
    private JSpinner spinStartDate;
    private JSpinner spinDueDate;
    private JSpinner spinEndDate;
    private JComboBox<String> cbAssignee;

    public EditDeleteTask(int taskId, int projectId, String projectName) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.projectName = projectName;

        buildCustomUI();
        loadExistingTaskData();
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
        cbPriority.setPreferredSize(new Dimension(150, 30));
        cbPriority.setBackground(new Color(255, 240, 240));
        addFormRow(propsPanel, gbc, row++, "⚠ Priority", cbPriority);

        JPanel datesPanel = new JPanel(new GridLayout(3, 2, 5, 10));
        datesPanel.setBackground(new Color(250, 250, 252));
        spinStartDate = createDateSpinner();
        spinDueDate = createDateSpinner();
        spinEndDate = createDateSpinner(); // UI only
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

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(250, 250, 252));
        footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton btnDelete = new JButton("🗑");
        btnDelete.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        btnDelete.setForeground(new Color(100, 100, 100));
        btnDelete.setContentAreaFilled(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.addActionListener(e -> deleteTask());
        footerPanel.add(btnDelete, BorderLayout.WEST);

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightButtons.setBackground(new Color(250, 250, 252));

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCancel.setBackground(new Color(240, 240, 240));
        btnCancel.setForeground(Color.DARK_GRAY);
        btnCancel.setPreferredSize(new Dimension(100, 35));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(e -> closeWindow());

        JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSave.setBackground(new Color(185, 28, 28)); // Merah sesuai gambar
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(new Dimension(100, 35));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(e -> updateTaskInDatabase());

        rightButtons.add(btnCancel);
        rightButtons.add(btnSave);
        footerPanel.add(rightButtons, BorderLayout.EAST);

        this.add(footerPanel, BorderLayout.SOUTH);
    } 

    private void loadExistingTaskData() {
        try {
            TaskControl taskControl = new TaskControl();
            Task task = taskControl.get(this.taskId);
            if (task != null) {
                txtTaskName.setText(task.getTitle());
                txtDescription.setText(task.getDescription());
                cbStatus.setSelectedItem(task.getStatus());
                cbPriority.setSelectedItem(task.getPriority());
                if (task.getStartDate() != null) {
                    spinStartDate.setValue(task.getStartDate());
                }
                if (task.getDueDate() != null) {
                    spinDueDate.setValue(task.getDueDate());
                }
            }
        } catch (DatabaseException e) {
            System.err.println("Gagal memuat data task: " + e.getMessage());
        }
    }

    private void updateTaskInDatabase() {
        try {
            TaskControl tc = new TaskControl();
            Task t = tc.get(this.taskId);
            if (t == null) return;

            t.setTitle(txtTaskName.getText().trim());
            t.setDescription(txtDescription.getText().trim());
            t.setStatus((TaskStatus) cbStatus.getSelectedItem());
            t.setPriority((TaskPriority) cbPriority.getSelectedItem());
            t.setStartDate((Date) spinStartDate.getValue());
            t.setDueDate((Date) spinDueDate.getValue());

            int result = tc.update(t);

            if (result > 0) {
                // Update Assignee
                if (cbAssignee.getSelectedIndex() > 0) {
                    String selectedAssignee = (String) cbAssignee.getSelectedItem();
                    int assigneeUserId = Integer.parseInt(selectedAssignee.split(" - ")[0]);
                    ProjectItemAssigneeDAO assigneeDAO = new ProjectItemAssigneeDAO();
                    assigneeDAO.assignUser(this.taskId, assigneeUserId);
                }

                JOptionPane.showMessageDialog(this, "Task berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                closeWindow();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengupdate task.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin menghapus Task ini?", 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
                
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                TaskControl tc = new TaskControl();
                tc.delete(this.taskId);
                JOptionPane.showMessageDialog(this, "Task berhasil dihapus!", "Terhapus", JOptionPane.INFORMATION_MESSAGE);
                closeWindow();
            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void closeWindow() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    private DefaultComboBoxModel<String> loadAssigneeModel() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("+ Select Assignee (Re-assign)");
        try {
            UserControl userControl = new UserControl();
            List<User> allUsers = userControl.fetchAll();
            if (allUsers != null) {
                for (User user : allUsers) {
                    model.addElement(user.getId() + " - " + user.getFullName());
                }
            }
        } catch (Exception e) {}
        return model;
    }

    private JSpinner createDateSpinner() {
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "EEEE, dd MMM yyyy"));
        spinner.setPreferredSize(new Dimension(180, 28));
        return spinner;
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, Component inputComp) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(new Color(108, 117, 125)); 
        label.setPreferredSize(new Dimension(120, 30));
        panel.add(label, gbc);

        gbc.gridx = 1; gbc.gridy = row; gbc.weightx = 1.0;
        panel.add(inputComp, gbc);
    }
}