package Test;

import Model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsersTest {
    private Users user;

    @BeforeEach
    public void init()
    {
        user = new Users();
    }

    @Test
    public void isStringSuccess()
    {
        String str = "Hello";
        String number = "12345";

        assertTrue(user.isString(str, str.length()));
        assertFalse(user.isString(number, number.length()));
    }

    @Test
    public void checkPhoneNumberSuccess()
    {
        String phoneNumber = "052-7463882";
        String str = "Hello";

        assertTrue(user.checkPhoneNumber(phoneNumber));
        assertFalse(user.checkPhoneNumber(str));
    }

    @Test
    public void checkEmailSuccess()
    {
        String email = "user@partner.co.il";
        String str = "wrongEmail@";

        assertTrue(user.checkEmail(email));
        assertFalse(user.checkEmail(str));
    }
}
