package package1;

import java.awt.*;
import java.awt.geom.*;

public class ComplexStroke implements Stroke {
    private static final int AMPLITUDE = 10;
    private static final Stroke STROKE = new BasicStroke(1.0f);

    @Override
    public Shape createStrokedShape(final Shape shape) {
        final GeneralPath generalPath = new GeneralPath();

        final float[] floats = new float[6];
        for (final PathIterator pathIterator = shape.getPathIterator(null); !pathIterator.isDone(); pathIterator.next()) {
            final int type = pathIterator.currentSegment(floats);
            switch (type) {
                case PathIterator.SEG_CUBICTO:
                case PathIterator.SEG_QUADTO:
                case PathIterator.SEG_LINETO: {
                    final double x1 = generalPath.getCurrentPoint().getX();
                    final double y1 = generalPath.getCurrentPoint().getY();
                    final double x2 = floats[0];
                    final double y2 = floats[1];
                    final double x0 = (x2 + x1) / 2;
                    final double y0 = (y2 + y1) / 2;
                    final double angle = Math.atan((y2 - y1) / (x2 - x1));
                    final int direction = getDirection(x1, x2);
                    final double sinD = AMPLITUDE * Math.sin(angle) * direction;
                    final double cosD = AMPLITUDE * Math.cos(angle) * direction;
                    generalPath.lineTo(x1 - sinD, y1 + cosD);
                    generalPath.lineTo(x0 - sinD, y0 + cosD);
                    generalPath.lineTo(x0 + sinD, y0 - cosD);
                    generalPath.lineTo(x2 + sinD, y2 - cosD);
                    generalPath.lineTo(x2, y2);
                    break;
                }
                case PathIterator.SEG_MOVETO:
                    generalPath.moveTo(floats[0], floats[1]);
                    break;
                case PathIterator.SEG_CLOSE:
                    generalPath.closePath();
                    break;
                default:
                    break;
            }
        }

        return STROKE.createStrokedShape(generalPath);
    }

    private static int getDirection(final double x1, final double x2) {
        return x2 - x1 >= 0 ? -1 : 1;
    }
}
