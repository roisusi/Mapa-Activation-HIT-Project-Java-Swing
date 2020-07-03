package View;

import Model.NumberRanges;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class NumberRangesViewModel extends AbstractTableModel {

    private final int MAXNUMBERS = 1000;
    private String[] colName = {"From", "To", "Count"};
    private static int moreRows = 0;
    private  static ArrayList<String> fromRange = new ArrayList<>();
    private  static ArrayList<String> toRange = new ArrayList<>();
    private int difference;

    public NumberRangesViewModel() {
        for (int i = 0 ; i < MAXNUMBERS ; i++) {
            fromRange.add("");
        }
        if (ActivationsMoves.SessionId.getFromRange() !=null){
            fromRange = ActivationsMoves.SessionId.getFromRange();
        }
        for (int i = 0 ; i < MAXNUMBERS ; i++) {
            toRange.add("");
        }
        if (ActivationsMoves.SessionId.getFromRange() !=null){
            toRange = ActivationsMoves.SessionId.getToRange();
        }
    }

    @Override
    public int getRowCount() {
        return moreRows;
    }

    public static void setMoreRows() {
        moreRows++;
    }
    public static void removeRows(int rowIndex) {
        if(moreRows != 0) {
            fromRange.remove(rowIndex);
            toRange.remove(rowIndex);
            moreRows--;
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return fromRange.get(rowIndex);
            case 1:
                return toRange.get(rowIndex);
            case 2:
                if(toRange.get(rowIndex).equals("") || fromRange.get(rowIndex).equals("")) {
                    difference = 0;
                }
                else
                    difference = Integer.parseInt(toRange.get(rowIndex)) -  Integer.parseInt(fromRange.get(rowIndex));

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
                if (from.matches("[0-9]+") || from.contains("-")) {
                    if (from.contains("-")) {
                        from = from.replace("-", "");
                    }
                    if(fromRange.get(rowIndex).equals(""))
                        fromRange.add(rowIndex,from);
                    else {
                        fromRange.remove(rowIndex);
                        fromRange.add(rowIndex,from);
                    }
                    System.out.println("From " + fromRange.get(rowIndex) + " in index " + rowIndex);
                }
                ActivationsMoves.SessionId.setFromRange(fromRange);

                break;
            case 1:
                String to = (String)aValue;
                if (to.matches("[0-9]+") || to.contains("-")) {
                    if (to.contains("-")) {
                        to = to.replace("-", "");
                    }
                    if(toRange.get(rowIndex).equals(""))
                        toRange.add(rowIndex,to);
                    else {
                        toRange.remove(rowIndex);
                        toRange.add(rowIndex,to);
                    }
                    System.out.println("From " + toRange.get(rowIndex) + " in index " + rowIndex);
                }
                ActivationsMoves.SessionId.setToRange(toRange);

                break;

        }
    }
}
