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
	public void dragEnter(final DragSourceDragEvent dragEvent) {
		// not needed
	}

	@Override
	public void dragExit(final DragSourceEvent sourceEvent) {
		// not needed
	}

	@Override
	public void dragOver(final DragSourceDragEvent dragEvent) {
		// not needed
	}

	@Override
	public void dropActionChanged(final DragSourceDragEvent dragEvent) {
		// not needed
	}

}
