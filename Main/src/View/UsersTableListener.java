package View;

import java.sql.SQLException;
import java.util.List;


public interface UsersTableListener {
    public void rowDelete(int row);
    public void rowEdit(List usersList, List loginList) throws SQLException;
}
