package View;

import Model.Users;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UsersTableModel extends AbstractTableModel {
    private List<Users> db;
    private String[] colName = {"First name","Last name","Email","Phone number","Type"};

    public UsersTableModel() {
    }

    public void setData(List<Users> db)
    {
        this.db = db;
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Users user = db.get(rowIndex);
        switch (columnIndex){
            case 0:
                return user.getFirstName();
            case 1:
                return user.getLastName();
            case 2:
                return user.getEmail();
            case 3:
                return user.getPhoneNumber();
            case 4:
                return user.getUsersType();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }
}
