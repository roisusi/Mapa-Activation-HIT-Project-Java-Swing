package View;

import Controller.ActivationSipController;
import Model.ActivationFormSip;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CalenderPanelModel extends AbstractTableModel {
    private ActivationSipController dbSip;

    private String[] colName = {"מומחה","מנהל פרוייקט","שם הלקוח","תאריך הפעלה","סוג הפעלה","הפעלה מאושרת?","נסיון הפעלה","תאריך עדכון אחרון"};
    public CalenderPanelModel() {
        dbSip = new ActivationSipController();
    }
    public void setData(ActivationSipController db)
    {
        this.dbSip = db;
    }
    @Override
    public int getRowCount() {
        return dbSip.getSipActivation().size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        dbSip.getSipActivation().get(rowIndex);
        switch (columnIndex){
            case 0:
                return dbSip.getSipActivation().get(rowIndex).getExpertFirstName();
            case 1:
                return dbSip.getSipActivation().get(rowIndex).getProjectManagerFirstName();
            case 2:
                return dbSip.getSipActivation().get(rowIndex).getCustomerName();
            case 3:
                return dbSip.getSipActivation().get(rowIndex).getDatePicker();
            case 4:
                return dbSip.getSipActivation().get(rowIndex).getActivationType();
            case 5:
                return dbSip.getSipActivation().get(rowIndex).getStatus();
            case 6:
                return dbSip.getSipActivation().get(rowIndex).getNumOfFails();
            case 7:
                return dbSip.getSipActivation().get(rowIndex).getLastUpdate();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }
}
