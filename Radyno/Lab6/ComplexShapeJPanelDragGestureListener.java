package package1;

import java.awt.Cursor;
import java.awt.dnd.*;

public class ComplexShapeJPanelDragGestureListener implements
		DragGestureListener {
	ComplexShapeJPanel panel;
	
	public ComplexShapeJPanelDragGestureListener(final ComplexShapeJPanel panel) {
		this.panel = panel;
	}

	@Override
	public void dragGestureRecognized(final DragGestureEvent event) {
		final ComplexShape dragScribble;
		try {			
			dragScribble = (ComplexShape) panel.complexShape.clone();
		} catch(final NullPointerException e) {
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
        event.startDrag(cursor, dragScribble, new ComplexShapeJPanelDragSourceListener(this.panel));
	}

}
