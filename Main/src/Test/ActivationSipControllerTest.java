package Test;

import Controller.ActivationSipController;
import Model.ActivationFormSip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class ActivationSipControllerTest {
    //@Mock
    //private String name;
    private ActivationSipController activationSipController;
    private List<ActivationFormSip> activationFormSipList = new LinkedList<ActivationFormSip>();

    @BeforeEach
    public void init() {
        activationSipController = new ActivationSipController();
    }

    @Test
    public void getSipActivationSuccess() throws Exception {
        //ActivationFormSip activationFormSip = new ActivationFormSip("customerID", "customerName", "contactName", "customerPhoneNumber", "customerEmail", "customerTechName", "customerTechPhoneNumber", "pbxType", "typeOfCalls", "identificationType", 10, "snbNumber", "areaCode", "emergencyCity", "callOutSideCountry", "crNumber", "trunkNumber", "datePicker", "wanAddress", "lanAddress", "ipAddress", "internetUser", "infrastructure", "routerType", "CODEC", 10, "signalAddress", "mediaAddress", 80, "expertName", "connectionType", "projectManagerFirstName", "activationType", "status", 5, "lastUpdate");
        activationFormSipList = activationSipController.getSipActivation();
        assertTrue(activationFormSipList.isEmpty());
        assertNotNull(activationFormSipList);

        activationSipController.connect();
        activationSipController.loadActivationSipToList();
        activationFormSipList = activationSipController.getSipActivation();
        assertFalse(activationFormSipList.isEmpty());
        assertNotNull(activationFormSipList);

    }

    @Test
    public void getConnection() throws Exception {
        activationSipController.connect();
        Connection con = activationSipController.getConnection();
        assertNotNull(con);
    }

    @Test
    public void checkIPSuccess()
    {
        int ipA = 255;
        int ipB = 255;
        int ipC = 255;
        int ipD = 255;

        assertTrue(activationSipController.checkIP(ipA, ipB, ipC, ipD));
    }

    @Test
    public void checkIPFailt()
    {
        int ipA = 127;
        int ipB = 30;
        int ipC = 255;
        int ipD = 255;

        assertFalse(activationSipController.checkIP(ipA, ipB, ipC, ipD));
    }


}
