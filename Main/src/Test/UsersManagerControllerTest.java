package Test;

import Controller.UsersManagerController;
import Model.Login;
import Model.Users;
import Model.UsersType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.mockito.junit.jupiter.MockitoExtension;

//@ExtendWith(MockitoExtension.class)
public class UsersManagerControllerTest {

    private int id = 1;
    private String userName = "username";
    private String password = "password";

    private String firstName = "firstname";
    private String lastName = "lastname";
    private String email = "email";
    private String phoneNumber = "phonenumber";
    private UsersType type = UsersType.Expert;
    private int userNameId = 1;

    private Users user = new Users(id, firstName, lastName, email, phoneNumber, type, userNameId);
    private List<Users> usersList;
    private Login login = new Login(id, userName, password);
    private List<Login> loginList;
    private UsersManagerController usersManagerController;

    @BeforeEach
    public void init() {
        usersManagerController = new UsersManagerController();
    }

    @Test
    public void getSystemUsersSuccess()
    {
        loginList = usersManagerController.getSystemUsers();
        assertNotNull(loginList);
        assertTrue(loginList.isEmpty());

        loginList.add(login);
        loginList = usersManagerController.getSystemUsers();

        assertNotNull(loginList);
        assertFalse(loginList.isEmpty());
    }

    @Test
    public void getUsersSuccess()
    {
        usersList = usersManagerController.getUsers();
        assertNotNull(usersList);
        assertTrue(usersList.isEmpty());

        usersList.add(user);
        usersList = usersManagerController.getUsers();

        assertNotNull(usersList);
        assertFalse(usersList.isEmpty());
    }

    @Test
    public void isLoginUserAlreadyExistsSuccess()
    {
        loginList = usersManagerController.getSystemUsers();
        loginList.add(login);
        usersManagerController.setSystemUsers(loginList);

        assertTrue(usersManagerController.isLoginUserAlreadyExists(login));
    }

    @Test
    public void isUserAlreadyExistSuccess()
    {
        usersList = usersManagerController.getUsers();
        usersList.add(user);
        usersManagerController.setUsers(usersList);

        assertTrue(usersManagerController.isUserAlreadyExists(user));
    }

    @Test
    public void loginUserAuthenticationSuccess()
    {
        assertEquals(false, usersManagerController.loginUserAuthentication(userName, password));
    }

    @Test
    public void loadUsersFromDataBaseToListSuccess() throws Exception {
        usersManagerController.connect();
        usersManagerController.loadUsersFromDataBaseToList();
        usersList = usersManagerController.getUsers();
        assertNotNull(usersList);
        assertFalse(usersList.isEmpty());
    }

    @Test
    public void loadSystemUsersFromDataBaseToListSuccess() throws Exception {
        usersManagerController.connect();
        usersManagerController.loadSystemUsersFromDataBaseToList();
        loginList = usersManagerController.getSystemUsers();
        assertNotNull(loginList);
        assertFalse(loginList.isEmpty());
    }
}
