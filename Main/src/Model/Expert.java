package Model;

public class Expert extends Users{
    public Expert(int id, String firstName, String lastName, String email, String phoneNumber, UsersType usersType, int userNameId) {
        super(id, firstName, lastName, email, phoneNumber, usersType, userNameId);
    }

    public Expert(Users other) {
        super(other);
    }
}
