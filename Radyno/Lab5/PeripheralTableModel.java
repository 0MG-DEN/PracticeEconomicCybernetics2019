package package1;

import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class PeripheralTableModel implements TableModel {
    private static final int RAND_BASE_VAL = 10;
    private static final int RAND_MAX_VAL = 5;
    private static final int COST_RANGE = 1000;
    private static final String[] columns = new String[]{"Name", "Brand", "Cost"};

    private List<TableModelListener> listeners = new ArrayList<>();
    private List<Peripheral> list;

    private static int getRandomValue() {
        return (int) (RAND_BASE_VAL + Math.random() * RAND_MAX_VAL);
    }

    public PeripheralTableModel(final boolean randomize) {
        this.list = new ArrayList<>();
        if (randomize) {
            if (Peripheral.brandsList.isEmpty()) {
                Peripheral.createBrandsList();
            }
            final int itemCount = getRandomValue();
            for (int i = 0; i < itemCount; ++i) {
                this.add(getRandomPeripheral());
            }
        }
    }

    public static Peripheral getRandomPeripheral() {
        final char[] name = new char[getRandomValue()];
        for (int i = 0; i < name.length; ++i) {
            name[i] = (char) ('a' + Math.random() * ('z' - 'a'));
        }
        return new Peripheral(
                new String(name),
                Peripheral.getRandomBrand(),
                (int) (Math.random() * COST_RANGE));
    }

    public void add(final Peripheral peripheral) {
        this.list.add(peripheral);
    }

    public void sort(final Comparator<Peripheral> comparator) {
        this.list.sort(comparator);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(final int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
                return String.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        try {
            Peripheral p = list.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return p.getName();
                case 1:
                    return p.getBrandName();
                case 2:
                    return String.valueOf(p.getCost()) + '$';
                default:
                    throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            return Object.class;
        }
    }

    @Override
    public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {

    }

    @Override
    public void addTableModelListener(final TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(final TableModelListener l) {
        listeners.remove(l);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final Peripheral p : list) {
            sb.append(p.toString());
            sb.append(" \r\n");
        }
        return sb.toString();
    }
}
