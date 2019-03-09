package package1;

import java.awt.*;
import java.awt.geom.*;
import java.awt.print.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class ComplexShape implements Shape, Printable {
    private final Area shapeArea;
    private static final int SIZE = 100;
    private static final int FONT_SIZE = 10;
    private static final int ANGLE_STEP = 6;
    private static final int OUTER_SHAPE_SIZE = 360 / ANGLE_STEP;
    private static final int INNER_SHAPE_SIZE = 180 / ANGLE_STEP;
    private static final double START_ANGLE = -180;
    private static final double OUTER_SHAPE_ANGLE = 180;
    private static final double INNER_SHAPE_ANGLE = 360;

    public ComplexShape() {
        final AffineTransform transform = new AffineTransform();
        transform.rotate(Math.PI / 2);
        transform.scale(0.5, 0.5);

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

    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex) throws PrinterException {

        switch (pageIndex) {
            case 0: {
                pageFormat.setOrientation(PageFormat.LANDSCAPE);
                final Graphics2D g2d = (Graphics2D) graphics;
                final ComplexShape shape = new ComplexShape();
                final double moveX = pageFormat.getImageableX() + pageFormat.getImageableWidth() / 2;
                final double moveY = pageFormat.getImageableY() + getBounds().height / 2;
                final AffineTransform transform = AffineTransform.getTranslateInstance(moveX, moveY);
                final Shape transformedShape = transform.createTransformedShape(shape);
                final ComplexStroke stroke = new ComplexStroke();
                g2d.setColor(Color.black);
                g2d.setStroke(stroke);
                g2d.draw(transformedShape);
                return PAGE_EXISTS;
            }
            case 1: {
                try {
                    pageFormat.setOrientation(PageFormat.PORTRAIT);
                    //fileName!
                    final Path path = Paths.get("G:\\Java-УП (задания)\\JavaAPI_4\\package1\\ComplexShape.java");
                    final Stream<String> stringSteam = Files.lines(path, Charset.forName("windows-1251"));
                    final Font font = new Font("Arial", Font.PLAIN, FONT_SIZE);
                    graphics.setFont(font);
                    int moveY = FONT_SIZE;
                    for (final Iterator<String> stringIterator = stringSteam.iterator(); stringIterator.hasNext(); ) {
                        graphics.drawString(stringIterator.next(), (int) pageFormat.getImageableX(), (int) pageFormat.getImageableY() + moveY);
                        moveY += FONT_SIZE;
                    }
                    return PAGE_EXISTS;
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                    return NO_SUCH_PAGE;
                }
            }
            default: {
                return NO_SUCH_PAGE;
            }
        }
    }
}
