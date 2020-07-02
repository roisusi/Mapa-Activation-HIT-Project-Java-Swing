package View;

import javax.swing.table.AbstractTableModel;

public class NumberRangesViewModel extends AbstractTableModel {

    private String[] colName = {"From", "To", "Count"};
    private static int moreRows = 0;
    private static int fromRange[] = new int[moreRows];
    private static int toRange[] = new int[moreRows];
    private int difference;


    @Override
    public int getRowCount() {
        return moreRows;
    }

    public static void setMoreRows() {
        moreRows++;
        fromRange = new int[moreRows];
        toRange = new int[moreRows];

    }


    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return fromRange[rowIndex];
            case 1:
                return toRange[rowIndex];
            case 2:
                difference = toRange[rowIndex] - fromRange[rowIndex];
                return difference;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(rowIndex == -1 && columnIndex == -1)
            return false;
        else
            return true;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                String from = (String)aValue;
                fromRange[rowIndex] = Integer.parseInt(from);
                System.out.println("From " + fromRange[rowIndex] + "in index " + rowIndex);
                break;
            case 1:
                String to = (String)aValue;
                toRange[rowIndex] = Integer.parseInt(to);                break;

        }
    }
}
