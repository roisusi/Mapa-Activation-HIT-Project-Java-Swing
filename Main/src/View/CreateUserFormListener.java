package View;

import java.sql.SQLException;
import java.util.EventListener;

public interface CreateUserFormListener extends EventListener {
    public void formEventOccurred(CreateFormEvent e) throws SQLException;
}
