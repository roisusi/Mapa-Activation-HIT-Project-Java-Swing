package View;

import Model.ActivationForm;
import Model.ActivationFormSip;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CalanderPanelModel extends AbstractTableModel {
    private List<ActivationFormSip> db;
    private String[] colName = {"Expert Name","Project Manager","Customer Name","Date"};
    public CalanderPanelModel() {
    }
    public void setData(List<ActivationFormSip> db)
    {
        this.db = db;
    }
    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ActivationFormSip sip = db.get(rowIndex);
        switch (columnIndex){
            case 0:
                return sip.getAreaCode();
            case 1:
                return sip.getCustomerTechName();
            case 2:
                return sip.getCustomerName();
            case 3:
                return sip.getDatePicker();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }

}
