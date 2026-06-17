/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.panel.component;

/**
 *
 * @author aldio
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {

    public CustomHeaderRenderer() {
        setHorizontalAlignment(JLabel.LEFT);
        setOpaque(true);
        setBackground(Color.WHITE);
        setForeground(new Color(180, 20, 40)); // Warna font merah
        setFont(new Font("SansSerif", Font.BOLD, 13));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Garis bawah tebal berwarna merah pada header
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 20, 40)),
                BorderFactory.createEmptyBorder(0, 10, 0, 0) // Padding kiri
        ));
        return this;
    }
}
