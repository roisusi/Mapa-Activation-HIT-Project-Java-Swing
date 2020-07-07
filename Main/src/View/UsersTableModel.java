package View;

import Model.Users;
import Model.Login;
import Model.UsersType;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UsersTableModel extends AbstractTableModel {
    private List<Users> userList;
    private List<Login> loginList;
    private String[] colName = {"שם פרטי","שם משפחה","דואר אלקטרוני","טלפון","סוג משתמש", "שם משתמש", "סיסמא"};

    public UsersTableModel() {
    }

    public void setData(List<Users> userList, List<Login> loginList)
    {
        this.userList = userList;
        this.loginList = loginList;
    }

    public List<Login> getLoginList() {
        return loginList;
    }

    public List<Users> getUserList() {
        return userList;
    }

    @Override
    public int getRowCount() { return userList.size(); }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Users user = userList.get(rowIndex);
        Login loginUser = null;

        for(Login login: loginList) {
            if(login.getId() == user.getUserNameId())
                loginUser = login;
        }

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
            case 5:
                return loginUser.getUserName();
            case 6:
                return loginUser.getPassword();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Users user = userList.get(rowIndex);
        Login loginUser = null;
        int index = 0;
        int size = loginList.size();

        for (int i = 0; i < size; i++)
        {
            if(loginList.get(i).getId() == user.getUserNameId())
                index = i;
        }

        switch (columnIndex){
            case 0:
                userList.get(rowIndex).setFirstName(value.toString());
                //user.setFirstName(value.toString());
                break;
            case 1:
                userList.get(rowIndex).setLastName(value.toString());
                //user.setLastName(value.toString());
                break;
            case 2:
                userList.get(rowIndex).setEmail(value.toString());
                //user.setEmail(value.toString());
                break;
            case 3:
                userList.get(rowIndex).setPhoneNumber(value.toString());
                //user.setPhoneNumber(value.toString());
                break;
            case 4:
                userList.get(rowIndex).setUsersType(UsersType.valueOf(value.toString()));
                //user.setUsersType(UsersType.valueOf(value.toString()));
                break;
            case 5:
                loginList.get(index).setUserName(value.toString());
                //loginUser.setUserName(value.toString());
                break;
            case 6:
                loginList.get(index).setPassword(value.toString());
                //loginUser.setPassword(value.toString());
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
