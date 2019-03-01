package package1;

import javax.swing.*;
import java.awt.*;

public class ComplexShapeJPanel extends JPanel {

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        final ComplexShape shape = new ComplexShape(this);
        final ComplexStroke stroke = new ComplexStroke();
        g2d.setColor(Color.black);
        g2d.setStroke(stroke);
        g2d.draw(shape);
    }
}
