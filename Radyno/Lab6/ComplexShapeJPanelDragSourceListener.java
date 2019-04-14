package package1;

import java.awt.dnd.*;

public class ComplexShapeJPanelDragSourceListener implements DragSourceListener {
	ComplexShapeJPanel panel;
	
	public ComplexShapeJPanelDragSourceListener(final ComplexShapeJPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void dragDropEnd(final DragSourceDropEvent event) {
        if (event.getDropSuccess()) {
            panel.setShape(null);
            panel.repaint();
        }
	}

	@Override
	public void dragEnter(final DragSourceDragEvent arg0) {
		// not needed
	}

	@Override
	public void dragExit(final DragSourceEvent arg0) {
		// not needed
	}

	@Override
	public void dragOver(final DragSourceDragEvent arg0) {
		// not needed
	}

	@Override
	public void dropActionChanged(final DragSourceDragEvent arg0) {
		// not needed
	}

}
