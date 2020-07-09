package View;

import java.sql.SQLException;
import java.util.EventListener;

public interface GetUserFromUsersListener extends EventListener {
    public void setUsers(CreateFormEvent e) throws SQLException;
}
