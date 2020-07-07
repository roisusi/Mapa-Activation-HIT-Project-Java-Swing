package View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Model.Users;
import Model.Login;

public interface UsersTableListener {
    public void rowDelete(int row);
    //public void rowEdit(ArrayList rowsList, ArrayList columnsList, ArrayList valuesList);
    public void rowEdit(List usersList, List loginList) throws SQLException;
}
