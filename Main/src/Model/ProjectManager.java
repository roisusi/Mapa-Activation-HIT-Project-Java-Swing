package Model;

public class ProjectManager extends Users {
    public ProjectManager(int id, String firstName, String lastName, String email, String phoneNumber, UsersType usersType, int userNameId) {
        super(id, firstName, lastName, email, phoneNumber, usersType, userNameId);
    }

    public ProjectManager(Users other) {
        super(other);
    }

    public boolean reports() { return false; }

    public boolean deleteForm() { return true; }

    public boolean editForm() { return true; }

    public boolean createForm() { return true; }

    public boolean manageUsers() { return false; }

    public boolean editUsers() { return false; }
}
