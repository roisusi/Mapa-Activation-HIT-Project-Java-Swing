package View;

public interface CalenderTableListener {
    public void rowDelete(int row);
    public void addExpertUser(int row , String firstName);
    public void setStatus(String status,int row);
}