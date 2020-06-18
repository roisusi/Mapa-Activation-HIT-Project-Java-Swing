package View;

import javax.swing.table.AbstractTableModel;

public class CalanderPanelModel extends AbstractTableModel {
    private String[] colName = {"Expert Name","Project Manager","Customer Name","Date"};
    public CalanderPanelModel() {

    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }
}
