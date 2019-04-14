package package1;

import java.awt.Color;
import java.awt.dnd.*;
import java.awt.datatransfer.Transferable;

public class ComplexShapeJPanelDropTargetListener implements DropTargetListener {
	private static final Color DROP_COLOR = new Color(205, 225, 245);
	ComplexShapeJPanel panel;
	
	public ComplexShapeJPanelDropTargetListener(final ComplexShapeJPanel panel) {
		this.panel = panel;
	}
	
    @Override
    public void drop(final DropTargetDropEvent dropEvent) {
		panel.setBackgroundColor(Color.white);

		if (dropEvent.isDataFlavorSupported(ComplexShape.complexShapeDF) && panel.complexShape == null) {
        	dropEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
        } else {
        	dropEvent.rejectDrop();
            return;
        }
        
        final Transferable transferable = dropEvent.getTransferable();
        final ComplexShape complexShape;
        
        try {
        	complexShape = (ComplexShape) transferable.getTransferData(ComplexShape.complexShapeDF);
        } catch (final Exception ex) {
        	dropEvent.dropComplete(false);
            return;
        }
        
        panel.setShape(complexShape);
    	dropEvent.dropComplete(true);
    }

	@Override
	public void dragEnter(final DropTargetDragEvent dragEvent) {
		panel.setBackgroundColor(DROP_COLOR);
        if (dragEvent.isDataFlavorSupported(ComplexShape.complexShapeDF)) {
        	dragEvent.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
        }
	}

	@Override
	public void dragExit(final DropTargetEvent targetEvent) {
		panel.setBackgroundColor(Color.white);
	}

	@Override
	public void dragOver(final DropTargetDragEvent dragEvent) {
		// not needed	
	}

	@Override
	public void dropActionChanged(final DropTargetDragEvent dragEvent) {
		// not needed
	}
}
