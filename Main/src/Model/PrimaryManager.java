package Model;

public class PrimaryManager extends Users {
    public PrimaryManager(int id, String firstName, String lastName, String email, String phoneNumber, UsersType usersType, int userNameId) {
        super(id, firstName, lastName, email, phoneNumber, usersType, userNameId);
    }

    public PrimaryManager(Users other) {
        super(other);
    }

    public boolean getReports() { return true; }

    public boolean deleteForm() { return true; }

    public boolean editForm() { return true; }

    public boolean createForm() { return true; }

    public boolean manageUsers() { return true; }

    public boolean editUsers() { return true; }
}
