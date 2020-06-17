package View;

import Model.Users;
import Model.Login;

import java.sql.SQLException;
import java.util.EventListener;

public interface CreateUserFormListener extends EventListener {
    public void formEventOccurred(Users user, Login login) throws SQLException;
}
