package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;

public final class Main {
    private static final int WINDOW_HEIGHT = 500, WINDOW_WIDTH = 400;

    private Main() {}

    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ComplexShapeJPanel shapePanel = new ComplexShapeJPanel();

        final ComplexShapeJPanelDropTargetListener targetListener =
        	new ComplexShapeJPanelDropTargetListener(shapePanel);

        final ComplexShapeJPanelDragGestureListener gestureListener =
        	new ComplexShapeJPanelDragGestureListener(shapePanel);

        final DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(
        		shapePanel, DnDConstants.ACTION_COPY_OR_MOVE, gestureListener
        		);

        final DropTarget dropTarget =
        	new DropTarget(shapePanel, targetListener);
        
        shapePanel.setDropTarget(dropTarget);
        
        final JMenuBar menuBar = new JMenuBar();
        final JMenu shapeMenu = new JMenu("Shape");
        final JMenuItem addItem = new JMenuItem("Add");
        addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent actionEvent) {
				shapePanel.setShape(new ComplexShape());
			}
        });
        final JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent actionEvent) {
				shapePanel.setShape(null);
			}
        });
        shapeMenu.add(addItem);
        shapeMenu.add(deleteItem);
        
        final JMenu colorMenu = new JMenu("Color");
        final JMenuItem redItem = new JMenuItem("Red");
        redItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent actionEvent) {
				shapePanel.setShapeColor(Color.red);
			}
        });
        final JMenuItem greenItem = new JMenuItem("Green");
        greenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent actionEvent) {
				shapePanel.setShapeColor(Color.green);
			}
        });
        final JMenuItem blueItem = new JMenuItem("Blue");
        blueItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent actionEvent) {
				shapePanel.setShapeColor(Color.blue);
			}
        });
        colorMenu.add(redItem);
        colorMenu.add(greenItem);
        colorMenu.add(blueItem);
        
        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(shapePanel, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}