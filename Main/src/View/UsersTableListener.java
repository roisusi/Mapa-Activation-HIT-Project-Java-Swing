package View;

import java.sql.SQLException;

public interface UsersTableListener {
    public void rowDelete(int row);
    public void rowEdit() throws SQLException;
}
