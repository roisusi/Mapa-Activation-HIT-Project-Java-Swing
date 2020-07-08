package Test;

import Controller.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void init() {
        controller = new Controller();
    }

    @Test
    public void getConnection() throws Exception {
        controller.connect();
        Connection con = controller.getConnection();
        assertNotNull(con);
    }


}
