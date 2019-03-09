package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

public final class Main {
    private static final int WINDOW_HEIGHT = 500, WINDOW_WIDTH = 400;
    private static boolean added = false;

    private static boolean isAdded() {
        return added;
    }

    private static void setAdded(boolean added) {
        Main.added = added;
    }

    private Main() {}

    public static void main(final String[] args) {
        final JFrame frame = new JFrame();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final ComplexShapeJPanel panel = new ComplexShapeJPanel();
        final PrintPreviewJPanel testPanel = new PrintPreviewJPanel();
        final JScrollPane scrollPane = new JScrollPane(testPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        final JLabel statusLabel = new JLabel(" ");

        final JMenuBar menuBar = new JMenuBar();
        final JMenuItem printItem = new JMenuItem("Print");
        printItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    if(printerJob.printDialog()) {
                        statusLabel.setText("Printing...");
                        PageFormat pageFormat = printerJob.defaultPage();
                        pageFormat = printerJob.pageDialog(pageFormat);
                        final ComplexShape shape = new ComplexShape();
                        printerJob.setPrintable(shape ,pageFormat);
                        printerJob.print();
                        statusLabel.setText("Print success.");
                    } else {
                        throw new PrinterException();
                    }
                } catch (PrinterException pe) {
                    statusLabel.setText("Print failed.");
                }
            }
        });

        final JMenu previewMenu = new JMenu("Print preview");
        final JMenuItem page1PreviewItem = new JMenuItem("Preview page 1");
        page1PreviewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testPanel.setIndex(0);
                if(!Main.isAdded()) {
                    frame.getContentPane().remove(panel);
                    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                    Main.setAdded(true);
                }
                frame.revalidate();
                frame.repaint();
            }
        });
        final JMenuItem page2PreviewItem = new JMenuItem("Preview page 2");
        page2PreviewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testPanel.setIndex(1);
                if(!Main.isAdded()) {
                    frame.getContentPane().remove(panel);
                    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                    Main.setAdded(true);
                }
                frame.revalidate();
                frame.repaint();
            }
        });

        menuBar.add(printItem);
        previewMenu.add(page1PreviewItem);
        previewMenu.add(page2PreviewItem);
        menuBar.add(previewMenu);
        frame.setJMenuBar(menuBar);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}