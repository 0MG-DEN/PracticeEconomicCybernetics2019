import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Main {
    private static final int
            WINDOW_WIDTH = 300,
            WINDOW_HEIGHT = 400,
            SHAPE_WIDTH = 200,
            SHAPE_HEIGHT = 100;

    private static ComplexShape shape = new ComplexShape(SHAPE_WIDTH, SHAPE_HEIGHT);
    private static double degAngle, radAngle;
    private static Stroke stroke;
    private static Color SHAPE_COLOR, FILL_COLOR;
    private static final Color BACKGROUND_COLOR = new Color(0xFFFFFF);

    public static void main(final String[] args) {
        try {
            float strokeWidth = Float.parseFloat(args[0]);
            stroke = new BasicStroke(strokeWidth);
            int shapeColorValue = Integer.parseInt(args[1], 16);
            SHAPE_COLOR = new Color(shapeColorValue);
            int fillColorValue = Integer.parseInt(args[2], 16);
            FILL_COLOR = new Color(fillColorValue);
        } catch (Exception ignored) {
            stroke = new BasicStroke(2.0f);
            SHAPE_COLOR = new Color(0x000000);
            FILL_COLOR = new Color(0xAABBCC);
        }

        final JFrame frame = new JFrame();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        final JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            public void paint(Graphics graphics) {
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setColor(Main.BACKGROUND_COLOR);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(Main.SHAPE_COLOR);

                //antialias
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //translating shape so its center matches the frame center
                g2d.translate(getWidth() / 2 - shape.getBounds2D().getCenterX(), getHeight() / 2 - shape.getBounds2D().getCenterY());

                //drawing
                g2d.setStroke(Main.stroke);
                g2d.draw(shape);
                g2d.setColor(Main.FILL_COLOR);
                g2d.fill(shape);
            }
        };

        final Timer timer = new Timer(5, arg0 -> {
            if (degAngle < 360)
                degAngle += 1;
            else
                degAngle = 1;
            radAngle = degAngle * Math.PI / 180;
            shape.setAngle(radAngle);

            panel.revalidate();
            panel.repaint();
        });
        timer.start();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
