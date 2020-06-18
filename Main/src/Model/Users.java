package Model;

public class Users {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected UsersType usersType;
    protected String email;
    protected String phoneNumber;
    private int userNameId;

    public Users(int id,String firstName, String lastName, String email,String phoneNumber, UsersType usersType,int userNameId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.usersType = usersType;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userNameId = userNameId;
    }

    public Users(Users other){
        this.id = other.id;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.usersType = other.usersType;
        this.email = other.email;
        this.phoneNumber = other.phoneNumber;
        this.userNameId = other.userNameId;
    }

    public int getUserNameId() {
        return userNameId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersType getUsersType() {
        return usersType;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
