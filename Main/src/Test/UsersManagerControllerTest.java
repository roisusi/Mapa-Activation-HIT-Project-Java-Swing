package Test;

import Controller.UsersManagerController;
import Model.Login;
import Model.Users;
import Model.UsersType;
import View.CreateFormEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    private String type = "Expert";
    private int userNameId = 1;

    private Users user = new Users(id, firstName, lastName, email, phoneNumber, UsersType.valueOf(type), userNameId);
    private List<Users> usersList;
    private Login login = new Login(id, userName, password);
    private List<Login> loginList;

    private CreateFormEvent event = new CreateFormEvent(firstName, lastName, email, phoneNumber, type, userName, password);
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
    public void createUserFailt()
    {
        assertThrows(Exception.class, () -> { usersManagerController.createUser(null);});
    }

    @Test
    public void isLoginUserAlreadyExistsSuccess()
    {
        usersManagerController.createUser(event);
        usersManagerController.addLoginToLoginList();

        assertFalse(usersManagerController.getSystemUsers().isEmpty());
        assertTrue(usersManagerController.isLoginUserAlreadyExists());
    }

    @Test
    public void isUserAlreadyExistSuccess()
    {
        usersManagerController.createUser(event);
        usersManagerController.addUserToUsersList();

        assertFalse(usersManagerController.getUsers().isEmpty());
        assertTrue(usersManagerController.isUserAlreadyExists());
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
