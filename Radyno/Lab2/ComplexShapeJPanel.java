package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class ComplexShapeJPanel extends JPanel {
    private final static int START_COLOR_VALUE = 0x111111, END_COLOR_VALUE = 0xEEEEEE;
    private final static Color START_COLOR = new Color(START_COLOR_VALUE);
    private final static Color END_COLOR = new Color(END_COLOR_VALUE);

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        BufferedImage bufferedImage = new BufferedImage(getWidth()/2, getHeight(), BufferedImage.TYPE_INT_RGB);
        final Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
        final GradientPaint backPaint = new GradientPaint(0, 0, START_COLOR, getWidth(), 0, END_COLOR);

        g2d.setPaint(backPaint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.red);
        g2d.fill(new ComplexShape(g2d, this));

        final Kernel kernel = getBlurKernel();
        final ConvolveOp convolveOp = new ConvolveOp(kernel);
        graphics.drawImage(bufferedImage, 0, 0, null);
        bufferedImage = convolveOp.filter(bufferedImage, null);
        graphics.drawImage(bufferedImage, bufferedImage.getWidth(), 0, null);
    }

    private static Kernel getBlurKernel() {
        final float[] kernelData = new float[] {
                1,  4,  6,  4,  1,
                4,  16, 24, 16, 4,
                6,  24, 36, 24, 6,
                1,  4,  6,  4,  1,
                4,  16, 24, 16, 4
        };
        for(int i = 0; i < kernelData.length; ++i) {
            kernelData[i] /= 256f;
        }
        return new Kernel(5,5,kernelData);
    }
}
