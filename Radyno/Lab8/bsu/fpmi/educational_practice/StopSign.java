package bsu.fpmi.educational_practice;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class StopSign extends Canvas {    
    private int diameter = 120;
    private Color backgroundColor = Color.DARK_GRAY;
    private Color signColor = Color.LIGHT_GRAY;

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(final int diameter) {
        this.diameter = diameter;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(final Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getSignColor() {
        return signColor;
    }

    public void setSignColor(final Color signColor) {
        this.signColor = signColor;
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        g.setColor(backgroundColor);
        g.fillOval(0, 0, diameter, diameter);
        final int rectX = (diameter >> 1) - (diameter >> 2);
        final int rectY = (diameter >> 1) - (diameter >> 3);
        g.setColor(signColor);
        g.fillRect(rectX, rectY, diameter >> 1, diameter >> 2);
    }
}
