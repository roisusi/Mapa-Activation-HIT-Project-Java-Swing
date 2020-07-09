package Test;

import Model.ActivationForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActivationFormTest extends ActivationForm{
    private ActivationFormTest activationFormTest;

    @BeforeEach
    public void init()
    {
        activationFormTest = new ActivationFormTest();
    }

    @Test
    public void checkSnbSuccess()
    {
        String snb = "36589561";
        String str = "Hello";

        assertTrue(activationFormTest.checkSnb(snb));
        assertThrows(NumberFormatException.class, () -> {activationFormTest.checkSnb(str);});
    }

    @Test
    public void checkPhoneNumberSuccess()
    {
        String phoneNumber = "052-7463882";
        String str = "Hello";

        assertTrue(activationFormTest.checkPhoneNumber(phoneNumber));
        assertFalse(activationFormTest.checkPhoneNumber(str));
    }

    @Test
    public void checkEmailSuccess()
    {
        String email = "user@partner.co.il";
        String str = "wrongEmail@";

        assertTrue(activationFormTest.checkEmail(email));
        assertFalse(activationFormTest.checkEmail(str));
    }

    @Test
    public void checkInputDigitsSuccess() {
        String phoneNumber= "0526475883";
        String str = "Hello";

        assertTrue(activationFormTest.checkInputDigits(phoneNumber));
        assertFalse(activationFormTest.checkInputDigits(str));
    }

    @Test
    public void checkEmptyCellsSuccess()
    {
        String empty = "";
        String str = "Hello";

        assertEquals(true, activationFormTest.checkEmptyCells(empty));
        assertEquals(false,activationFormTest.checkEmptyCells(str));
    }
}
