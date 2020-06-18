package Model;

public class PrimaryManager extends Users{
    public PrimaryManager(int id, String firstName, String lastName, String email, String phoneNumber, UsersType usersType, int userNameId) {
        super(id, firstName, lastName, email, phoneNumber, usersType, userNameId);
    }

    public PrimaryManager(Users other) {
        super(other);
    }
}
