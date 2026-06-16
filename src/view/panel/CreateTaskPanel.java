/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.panel;

/**
 *
 * @author aldio
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CreateTaskPanel extends JPanel {
    private JTable taskTable;
    private DefaultTableModel tableModel;

    public CreateTaskPanel() {
        setLayout(new BorderLayout(10, 10));
        setOpaque(false); 
        
        String[] columnNames = {"Task Name", "Deadline", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        
        taskTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        taskTable.setRowHeight(40);
        taskTable.setShowGrid(false);
        taskTable.setIntercellSpacing(new Dimension(0, 0));
        taskTable.getTableHeader().setBackground(new Color(0xC0392B));
        taskTable.getTableHeader().setForeground(Color.WHITE);
        taskTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        add(scrollPane, BorderLayout.CENTER);


        JButton btnAdd = new JButton("+ Tambah Tugas");
        btnAdd.setBackground(new Color(0xC0392B));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e -> tambahBaris());
        add(btnAdd, BorderLayout.SOUTH);
    }

    private void tambahBaris() {

        tableModel.addRow(new Object[]{"Tugas Baru", "16/06/2026", "In Progress"});
    }
}