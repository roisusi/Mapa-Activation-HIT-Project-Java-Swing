package Model;

public class ProjectManager extends Users{
    public ProjectManager(int id, String firstName, String lastName, String email, String phoneNumber, UsersType usersType, int userNameId) {
        super(id, firstName, lastName, email, phoneNumber, usersType, userNameId);
    }

    public ProjectManager(Users other) {
        super(other);
    }
}
