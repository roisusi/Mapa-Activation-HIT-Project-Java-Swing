package View;

import Model.ActivationFormSip;
import Model.Users;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CalenderPanelModel extends AbstractTableModel {
    private List<ActivationFormSip> dbSip;

    private String[] colName = {"מומחה","מנהל פרוייקט","שם הלקוח","תאריך הפעלה","סוג הפעלה","הפעלה מאושרת?","נסיון הפעלה","תאריך עדכון אחרון"};
    public CalenderPanelModel() {
    }
    public void setData(List<ActivationFormSip> db)
    {
        this.dbSip = db;
    }
    @Override
    public int getRowCount() {
        return dbSip.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ActivationFormSip sip = dbSip.get(rowIndex);
        switch (columnIndex){
            case 0:
                return sip.getFirstName();
            case 1:
                return sip.getProjectManagerFirstName();
            case 2:
                return sip.getCustomerName();
            case 3:
                return sip.getDatePicker();
            case 4:
                return sip.getActivationType();
            case 5:
                return sip.getStatus();
            case 6:
                return sip.getNumOfFails();
            case 7:
                return sip.getLastUpdate();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }
}
