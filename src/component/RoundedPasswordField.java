package component;

import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.JPasswordField;

public class RoundedPasswordField extends JPasswordField {
    
    private Shape shape;
    
    public RoundedPasswordField(int size) {
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
