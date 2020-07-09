package Model;

public class Users implements UserFunctionality{
    protected int id;
    protected String firstName;
    protected String lastName;
    protected UsersType usersType;
    protected String email;
    protected String phoneNumber;
    private int userNameId;

    public Users() {

    }

    public Users(String firstName, String lastName, String email,String phoneNumber, UsersType usersType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.usersType = usersType;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUserNameId() {
        return userNameId;
    }

    public void setUserNameId(int userNameId) { this.userNameId = userNameId; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public UsersType getUsersType() {
        return usersType;
    }

    public void setUsersType(UsersType usersType) { this.usersType = usersType; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public boolean reports() { return false; }

    @Override
    public boolean deleteForm() { return false; }

    @Override
    public boolean editForm() { return false; }

    @Override
    public boolean createForm() { return false; }

    @Override
    public boolean manageUsers() { return false; }

    @Override
    public boolean editUsers() { return false; }

    public boolean isString(String str, int size)
    {
        boolean flag = true;
        char[] strArray = str.toCharArray();

        for (int i = 0; i < size; i++) {
            if(!Character.isAlphabetic(strArray[i]))
                flag = false;
        }
        return flag;
    }

    public boolean checkPhoneNumber(String phone){
        String text = phone;
        int count=0;
        int firstThreeDigs=3;
        if (text.length() != 11)
            return false;
        if (text.contains("-"))
            count ++;
        // check 050,052,054,055,058
        if (count != 1 || text.indexOf("-") - firstThreeDigs != 0)
            return false;
        char ch = text.charAt(2);
        if (text.charAt(0) == '0'  && text.charAt(1) == '5' && (text.charAt(2) == '0' || text.charAt(2) == '2' || text.charAt(2) == '4' || text.charAt(2) == '5' || text.charAt(2) == '8' ))
            return true;
        return false;
    }

    public boolean checkEmail(String email){
        String text = email;
        int count =0;
        if (text.contains("@")){
            count++;
        }
        if (text.contains(".co.il") || text.contains(".com") || text.contains(".net")){
            if (count == 1)
                return true;
        }

        return false;
    }
}
