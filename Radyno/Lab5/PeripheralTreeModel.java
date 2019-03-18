package package1;

import java.util.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class PeripheralTreeModel implements TreeModel {

    public static final String[] TYPES = new String[]{"Input", "Output", "Storage", "Network", "testType"};

    private List<TreeModelListener> listeners = new ArrayList<>();
    private static final String root = "Peripherals";
    public PeripheralTableModel[] peripheralTables = new PeripheralTableModel[TYPES.length];

    public PeripheralTreeModel(final boolean randomize) {
        for (int i = 0; i < peripheralTables.length; ++i) {
            peripheralTables[i] = new PeripheralTableModel(randomize);
        }
    }

    public void sortItems(final Comparator<Peripheral> comparator) {
        for (final PeripheralTableModel table : peripheralTables) {
            table.sort(comparator);
        }
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(final Object parent, final int index) {
        if (parent == root) {
            return TYPES[index];
        }
        for (int i = 0; i < TYPES.length; ++i) {
            if (parent == TYPES[i] && index == 0) {
                return peripheralTables[i];
            }
        }
        return null;
    }

    @Override
    public int getChildCount(final Object parent) {
        if (parent == root) {
            return TYPES.length;
        }
        for (final String type : TYPES) {
            if (parent.equals(type)) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean isLeaf(final Object node) {
        if (node.equals(root)) {
            return false;
        }
        for (final String type : TYPES) {
            if (node == type) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getIndexOfChild(final Object parent, final Object child) {
        if (parent.equals(root)) {
            for (int i = 0; i < TYPES.length; ++i) {
                if (child == TYPES[i]) {
                    return i;
                }
            }
        }
        for (int i = 0; i < TYPES.length; ++i) {
            if (parent == TYPES[i] && child == peripheralTables[i]) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void valueForPathChanged(final TreePath path, final Object newValue) {

    }

    @Override
    public void addTreeModelListener(final TreeModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTreeModelListener(final TreeModelListener l) {
        listeners.remove(l);
    }
}
