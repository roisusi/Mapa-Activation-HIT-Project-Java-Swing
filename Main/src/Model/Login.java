package Model;

public class Login {
    private int id;
    private String userName;
    private String password;

<<<<<<< Updated upstream
    public Login() {
=======
    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
>>>>>>> Stashed changes
    }

    public Login(int id ,String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

<<<<<<< Updated upstream
=======
    public void setId(int id) { this.id = id; }

>>>>>>> Stashed changes
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
