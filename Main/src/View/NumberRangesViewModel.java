package View;

import Controller.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class NumberRangesViewModel extends AbstractTableModel {

    private final int MAXNUMBERS = 1000;
    private String[] colName = {"From", "To", "Count"};
    private int moreRows = 0;
    private ArrayList<String> fromRange = new ArrayList<>();
    private ArrayList<String> toRange = new ArrayList<>();
    private int difference;
    private int size;
    private NumberRangeController controller;


    public NumberRangesViewModel(ArrayList fromRange, ArrayList toRange) {
        this.fromRange = fromRange;
        this.toRange = toRange;
        controller = new NumberRangeController(this.fromRange,this.toRange);
    }
    public void setMoreRows() {
        fromRange.add("");
        toRange.add("");
        moreRows++;
    }
    public void removeRows(int rowIndex) {
        if(moreRows != 0) {
            fromRange.remove(rowIndex);
            toRange.remove(rowIndex);
            moreRows--;
        }
    }

    public void removeViewCells(){
        size = fromRange.size();
        int i=0,index=0;
        while(i<size){
            if(!fromRange.get(i).equals("") && !toRange.get(i).equals(""))
                index++;
            i++;
        }
        moreRows = index;
    }

    public void showEditRows(){
        for (int i=0 ; i < fromRange.size() ; i ++){
            moreRows++;
        }

    }

    public ArrayList fromTableList(){
        return fromRange;
    }

    public ArrayList toTableList(){
        return toRange;
    }

    @Override
    public int getRowCount() {
        return moreRows;
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
                if(fromRange.size()>0 && toRange.size()>0)
                if(controller.checkListIsEmpty(fromRange.get(rowIndex)) || controller.checkListIsEmpty(toRange.get(rowIndex))) {
                    difference = 0;
                }
                else
                    difference = Integer.parseInt(toRange.get(rowIndex)) -  Integer.parseInt(fromRange.get(rowIndex)) + 1;

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
        String newStr;
        switch (columnIndex) {
            case 0:
                newStr = controller.checkList((String)aValue);
                if (!controller.trueNumberIsraelPhoneCheck(newStr))
                    newStr = "";
                if(controller.checkListIsEmpty(newStr)) {
                    fromRange.add(rowIndex, newStr);
                }
                else {
                    fromRange.remove(rowIndex);
                    fromRange.add(rowIndex,newStr);
                }

                break;
            case 1:
                newStr = controller.checkList((String)aValue);
                if (!controller.trueNumberIsraelPhoneCheck(newStr))
                    newStr = "";
                if(controller.checkListIsEmpty(newStr)) {
                        toRange.add(rowIndex, newStr);
                }
                else {
                    toRange.remove(rowIndex);
                    toRange.add(rowIndex,newStr);
                }
                if(!controller.checkListIsEmpty(fromRange.get(rowIndex)) && !controller.checkListIsEmpty(toRange.get(rowIndex)) && !controller.negative(Integer.parseInt(fromRange.get(rowIndex)),Integer.parseInt(toRange.get(rowIndex)))){
                    newStr="";
                    toRange.remove(rowIndex);
                    toRange.add(rowIndex,newStr);
                    JOptionPane.showMessageDialog(null, "המספר ה-TO יותר קטן מה-FROM", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
        }
    }
}
