package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;

public class PrintPreviewJPanel extends JPanel {
    private int index = 0;

    public void setIndex(int index) {
        if (index > 1 || index < 0) {
            this.index = 0;
        }
        else {
            this.index = index;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        final BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.white);
        imageGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        imageGraphics.setColor(Color.black);
        try {
            final PageFormat pageFormat = new PageFormat();
            final ComplexShape shape = new ComplexShape();
            if (shape.print(imageGraphics, pageFormat, index) != Printable.PAGE_EXISTS) {
                throw new PrinterException();
            }
        } catch (PrinterException e) {
            imageGraphics.setColor(Color.red);
            imageGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        }
        g.drawImage(image, 0, 0, null);
    }
}
