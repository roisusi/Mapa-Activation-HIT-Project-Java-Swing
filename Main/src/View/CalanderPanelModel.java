package View;

import Model.ActivationFormSip;
import Model.Users;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CalanderPanelModel extends AbstractTableModel {
    private List<ActivationFormSip> dbSip;

    private String[] colName = {"מומחה","מנהל פרוייקט","שם הלקוח","תאריך","סוג הפעלה"};
    public CalanderPanelModel() {
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
        return 5;
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
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }

}
