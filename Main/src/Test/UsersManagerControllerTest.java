package Test;

import Controller.UsersManagerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static org.junit.Assert.*;

public class UsersManagerControllerTest {
    private UsersManagerController usersManagerController;

    @BeforeEach
    public void init() {
        System.out.println("init()");
        usersManagerController = new UsersManagerController();
    }

    @Test
    public void loginUserAuthenticationSuccess()
    {
        System.out.println("loginAuthentication()");
        String userName = "username";
        String password = "password";

        //assertEquals(true, usersManagerController.loginUserAuthentication(userName, password));
    }

}
