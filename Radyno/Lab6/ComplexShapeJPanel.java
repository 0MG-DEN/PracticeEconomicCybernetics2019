package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class ComplexShapeJPanel extends JPanel {
    public ComplexShape complexShape;
    public Color backgroundColor;

    public ComplexShapeJPanel() {
    	this(Color.black);
    }
    
    public ComplexShapeJPanel(final Color shapeColor) {
        this.backgroundColor = Color.white;
        this.complexShape = new ComplexShape(shapeColor);
    }

    public void setShape(final ComplexShape shape) {
    	if(this.complexShape == null || shape == null) {    		
    		this.complexShape = shape;
    		this.repaint();
    	}
    }
    
    public void setBackgroundColor(final Color color) {
    	this.backgroundColor = color;
    	this.repaint();
    }
    
    public void setShapeColor(final Color color) {
    	if(this.complexShape != null) {    		
    		this.complexShape.color = color;
    		this.repaint();
    	}
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if(this.complexShape == null) {
        	return;
        }

        final AffineTransform transform = AffineTransform.getTranslateInstance(getWidth() >> 1, getHeight() >> 1);
        final Shape shape = transform.createTransformedShape(complexShape);
        final ComplexStroke stroke = new ComplexStroke();
        g2d.setColor(complexShape.color);
        g2d.setStroke(stroke);
        g2d.draw(shape);
    }
}
