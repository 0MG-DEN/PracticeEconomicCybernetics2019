package package1;

import java.io.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.geom.*;

public class ComplexShape implements Shape, Transferable, Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	public static final DataFlavor COMPLEX_SHAPE_DF = new DataFlavor(ComplexShape.class, "COMPLEX_SHAPE_DF");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {COMPLEX_SHAPE_DF};

    private transient Area shapeArea;
    public Color color;
    private final Shape innerShape;
    private final Shape outerShape;
    private static final int SIZE = 100;
    private static final int ANGLE_STEP = 6;
    private static final int OUTER_SHAPE_SIZE = 360 / ANGLE_STEP;
    private static final int INNER_SHAPE_SIZE = 180 / ANGLE_STEP;
    private static final double START_ANGLE = -180;
    private static final double OUTER_SHAPE_ANGLE = 180;
    private static final double INNER_SHAPE_ANGLE = 360;

    private Area getShapeArea() {
    	if(this.shapeArea == null) { 
            this.shapeArea = new Area();
            this.shapeArea.add(new Area(outerShape));
            this.shapeArea.subtract(new Area(innerShape));
    	}
    	return this.shapeArea;	
    }

    public ComplexShape() {
		this(Color.black);
    }
    
    public ComplexShape(final Color color) {
    	this.color = color;
    	final AffineTransform transform = AffineTransform.getRotateInstance(Math.PI / 2);
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

        final Polygon outerPolygon = new Polygon(outerShapeX, outerShapeY, OUTER_SHAPE_SIZE);
        final Polygon innerPolygon = new Polygon(innerShapeX, innerShapeY, INNER_SHAPE_SIZE);
        this.innerShape = transform.createTransformedShape(innerPolygon);
        this.outerShape = transform.createTransformedShape(outerPolygon);
        
        getShapeArea(); //creating getShapeArea
    }

    @Override
    public Rectangle getBounds() {
        return this.getShapeArea().getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return this.getShapeArea().getBounds2D();
    }

    @Override
    public boolean contains(final double x, final double y) {
        return this.getShapeArea().contains(x, y);
    }

    @Override
    public boolean contains(final Point2D p) {
        return this.getShapeArea().contains(p);
    }

    @Override
    public boolean intersects(final double x, final double y, final double w, final double h) {
        return this.getShapeArea().intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(final Rectangle2D r) {
        return this.getShapeArea().intersects(r);
    }

    @Override
    public boolean contains(final double x, final double y, final double w, final double h) {
        return this.getShapeArea().contains(x, y, w, h);
    }

    @Override
    public boolean contains(final Rectangle2D r) {
        return this.getShapeArea().contains(r);
    }

    @Override
    public PathIterator getPathIterator(final AffineTransform at) {
        return this.getShapeArea().getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(final AffineTransform at, final double flatness) {
        return this.getShapeArea().getPathIterator(at, flatness);
    }

	@Override
	public Object getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(COMPLEX_SHAPE_DF)) {
            return this;
        } else {
        	throw new UnsupportedFlavorException(flavor);
        }
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS.clone();
	}

	@Override
	public boolean isDataFlavorSupported(final DataFlavor flavor) {
		return flavor.equals(COMPLEX_SHAPE_DF);
	}
	
    @Override
    public Object clone() {
    	try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new ComplexShape();
		}
    }
}
