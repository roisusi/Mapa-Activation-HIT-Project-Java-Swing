package View;

import Model.Users;
import Model.Login;

import java.sql.SQLException;
import java.util.EventListener;

public interface GetUserFromUsersListener extends EventListener {
    public void setUsers(Users user, Login login) throws SQLException;
}
