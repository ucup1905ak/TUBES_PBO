/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Component;

/**
 *
 * @author Joy
 */
import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {

    private int radius = 20;

    public RoundedPanel() {
        setOpaque(false);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(
                0, 0,
                getWidth(),
                getHeight(),
                radius,
                radius);

        g2.dispose();

        super.paintComponent(g);
    }
}