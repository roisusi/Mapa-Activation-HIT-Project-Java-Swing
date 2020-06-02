package View;


import Controller.Controller;
import Model.ActivationFormSip;
import Model.DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class HomePage extends JFrame {
    private HomePageCalenderMenu cal;
    private HomePageMenu menu;
    private UpperMenu upperMenu;
    private Controller controller;
    private DataBase db = new DataBase();
    private getDataFromSipListener getDataFromSipListener;

    public HomePage() {
        super("Mapa Activation");
        controller = new Controller();
        setLayout(new BorderLayout()); //set BorderLayout

        //-- Creation of Left Side --//
        menu = new HomePageMenu();

        //-- Creation of Right side --//
        cal = new HomePageCalenderMenu();
        cal.setData(controller.getSipActivation());
        try {
            controller.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.loadTheActivationSip();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cal.refresh();
        menu.setDataToCalender(new getDataFromSipListener() {
            @Override
            public void setActivation(List<ActivationFormSip> e) {
                cal.setData(e);
                cal.refresh();
            }
        });

        //-- Creation of UpperMenu --//
        upperMenu = new UpperMenu();

        //-- adding --//
        add(menu,BorderLayout.WEST);
        add(upperMenu,BorderLayout.NORTH);
        add(cal,BorderLayout.CENTER);

        //-- Frame Options -//
        setSize(1400, 800); // Size the Frame
        setMinimumSize(new Dimension(400, 500)); //set minimum
        setLocationRelativeTo(null); //Center the Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when i press X it will close
        setVisible(true); //show Frame
        controller.disconnect();
    }

}
