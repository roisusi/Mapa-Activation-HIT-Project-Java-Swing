package View;

import java.util.ArrayList;

public interface UsersTableListener {
    public void rowDelete(int row);
    public void rowEdit(ArrayList rowsList, ArrayList columnsList, ArrayList valuesList);
}
