package View;

import Controller.UsersManagerController;
import Model.UsersType;

import javax.swing.table.AbstractTableModel;

public class UsersTableModel extends AbstractTableModel {
    private UsersManagerController db;
    private String[] colName = {"שם פרטי","שם משפחה","דואר אלקטרוני","טלפון","סוג משתמש", "שם משתמש", "סיסמא"};

    public UsersTableModel() {
        db = new UsersManagerController();
    }

    public void setData(UsersManagerController db)
    {
        this.db = db;
    }

    @Override
    public int getRowCount() { return db.getUsers().size(); }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex){
            case 0:
                return db.getUsers().get(rowIndex).getFirstName();
            case 1:
                return db.getUsers().get(rowIndex).getLastName();
            case 2:
                return db.getUsers().get(rowIndex).getEmail();
            case 3:
                return db.getUsers().get(rowIndex).getPhoneNumber();
            case 4:
                return db.getUsers().get(rowIndex).getUsersType();
            case 5:
                return db.getSystemUsers().get(db.getLoginUserIndex(db.getUsers().get(rowIndex))).getUserName();
            case 6:
                return db.getSystemUsers().get(db.getLoginUserIndex(db.getUsers().get(rowIndex))).getPassword();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        switch (columnIndex){
            case 0:
                db.getUsers().get(rowIndex).setFirstName(value.toString());
                break;
            case 1:
                db.getUsers().get(rowIndex).setLastName(value.toString());
                break;
            case 2:
                db.getUsers().get(rowIndex).setEmail(value.toString());
                break;
            case 3:
                db.getUsers().get(rowIndex).setPhoneNumber(value.toString());
                break;
            case 4:
                db.getUsers().get(rowIndex).setUsersType(UsersType.valueOf(value.toString()));
                break;
            case 5:
                db.getSystemUsers().get(db.getLoginUserIndex(db.getUsers().get(rowIndex))).setUserName(value.toString());
                break;
            case 6:
                db.getSystemUsers().get(db.getLoginUserIndex(db.getUsers().get(rowIndex))).setPassword(value.toString());
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) { return true; }
}
