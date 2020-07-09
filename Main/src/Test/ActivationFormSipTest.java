package Test;

import Model.ActivationFormSip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ActivationFormSipTest {
    private ActivationFormSip activationFormSip;

    @BeforeEach
    public void init()
    {
        activationFormSip = new ActivationFormSip();
    }

    @Test
    public void checkIPSuccess()
    {
        int ipA = 255;
        int ipB = 255;
        int ipC = 255;
        int ipD = 255;

        assertTrue(activationFormSip.checkIP(ipA, ipB, ipC, ipD));
    }

    @Test
    public void checkIPFailt()
    {
        int ipA = 127;
        int ipB = 30;
        int ipC = 255;
        int ipD = 255;

        assertFalse(activationFormSip.checkIP(ipA, ipB, ipC, ipD));
    }

}
