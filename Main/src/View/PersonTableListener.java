package View;

public interface PersonTableListener {
    public void rowDelete(int row);
    //public void editRow(int row , int col);
    public void addExpertUser(int row , String firstName);
    public void fireChanges();
}