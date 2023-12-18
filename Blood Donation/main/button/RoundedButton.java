package main.button;

import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton(String label) {
        super(label);

        // Create a rounded border with a specified radius
        Border roundedBorder = new RoundedBorder(25); // 25 is the radius
        this.setBorder(roundedBorder);
        this.setContentAreaFilled(false); // This is important
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        super.paintComponent(g);
    }

    private static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
