/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component;

import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.JTextField;

/**
 *
 * @author aldio
 */
public class RoundedTextField extends JTextField {
    
    private Shape shape;
    
    public RoundedTextField(int size) {
        super(size);
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
        super.paintComponent(g);
        
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        if (hasFocus()) {
            g.setColor(new java.awt.Color(237, 237, 244));
        } else {
            g.setColor(new java.awt.Color(237, 237, 244));
        }
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
    }
}
