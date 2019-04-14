package package1;

import java.awt.Cursor;
import java.awt.dnd.*;

public class ComplexShapeJPanelDragGestureListener implements
		DragGestureListener {
	private ComplexShapeJPanel panel;
	
	public ComplexShapeJPanelDragGestureListener(final ComplexShapeJPanel panel) {
		this.panel = panel;
	}

	@Override
	public void dragGestureRecognized(final DragGestureEvent event) {
		final ComplexShape shape  = this.panel.getShape();
		if(shape == null) {
			return;
		}
        final Cursor cursor;
        switch (event.getDragAction()) {
        	case DnDConstants.ACTION_COPY:
        		cursor = DragSource.DefaultCopyDrop;
        		break;
        	case DnDConstants.ACTION_MOVE:
        		cursor = DragSource.DefaultMoveDrop;
        		break;
        	default:
        		return;
        }
		final ComplexShape dragScribble = (ComplexShape) shape.clone();
        event.startDrag(cursor, dragScribble, new ComplexShapeJPanelDragSourceListener(this.panel));
	}

}
