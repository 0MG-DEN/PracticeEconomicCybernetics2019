package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ComplexShape implements Shape {
    private final Area shapeArea;
    private static final int SIZE = 100, ANGLE_STEP = 6;
    private static final int OUTER_SHAPE_SIZE = 360 / ANGLE_STEP;
    private static final int INNER_SHAPE_SIZE = 180 / ANGLE_STEP;
    private static final double START_ANGLE = -180;
    private static final double OUTER_SHAPE_ANGLE = 180;
    private static final double INNER_SHAPE_ANGLE = 360;

    public ComplexShape(final JPanel panel) {
        final AffineTransform transform = new AffineTransform();
        transform.translate(panel.getWidth() / 2, panel.getHeight() / 2);
        transform.rotate(Math.PI / 2);

        final int[] innerShapeX = new int[INNER_SHAPE_SIZE];
        final int[] innerShapeY = new int[INNER_SHAPE_SIZE];
        final int[] outerShapeX = new int[OUTER_SHAPE_SIZE];
        final int[] outerShapeY = new int[OUTER_SHAPE_SIZE];

        double r;
        double angle = START_ANGLE;
        for (int i = 0; angle < OUTER_SHAPE_ANGLE; angle += ANGLE_STEP, ++i) {
            r = (int) (2 * SIZE * Math.cos(Math.PI * angle / (3 * 180)));
            outerShapeX[i] = (int) (r * Math.cos(Math.PI * angle / 180));
            outerShapeY[i] = (int) (r * Math.sin(Math.PI * angle / 180));
        }
        for (int i = 0; angle < INNER_SHAPE_ANGLE; angle += ANGLE_STEP, ++i) {
            r = (int) (2 * SIZE * Math.cos(Math.PI * angle / (3 * 180)));
            innerShapeX[i] = (int) (r * Math.cos(Math.PI * angle / 180));
            innerShapeY[i] = (int) (r * Math.sin(Math.PI * angle / 180));
        }

        Shape outerShape = new Polygon(outerShapeX, outerShapeY, OUTER_SHAPE_SIZE);
        Shape innerShape = new Polygon(innerShapeX, innerShapeY, INNER_SHAPE_SIZE);
        outerShape = transform.createTransformedShape(outerShape);
        innerShape = transform.createTransformedShape(innerShape);

        this.shapeArea = new Area();
        this.shapeArea.add(new Area(outerShape));
        this.shapeArea.subtract(new Area(innerShape));
    }

    @Override
    public Rectangle getBounds() {
        return this.shapeArea.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return this.shapeArea.getBounds2D();
    }

    @Override
    public boolean contains(final double x, final double y) {
        return this.shapeArea.contains(x, y);
    }

    @Override
    public boolean contains(final Point2D p) {
        return this.shapeArea.contains(p);
    }

    @Override
    public boolean intersects(final double x, final double y, final double w, final double h) {
        return this.shapeArea.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(final Rectangle2D r) {
        return this.shapeArea.intersects(r);
    }

    @Override
    public boolean contains(final double x, final double y, final double w, final double h) {
        return this.shapeArea.contains(x, y, w, h);
    }

    @Override
    public boolean contains(final Rectangle2D r) {
        return this.shapeArea.contains(r);
    }

    @Override
    public PathIterator getPathIterator(final AffineTransform at) {
        return this.shapeArea.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(final AffineTransform at, final double flatness) {
        return this.shapeArea.getPathIterator(at, flatness);
    }
}
