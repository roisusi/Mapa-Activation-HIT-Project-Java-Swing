package View;


import Controller.Contorller;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class HomePage extends JFrame {
    private HomePageCalenderMenu cal;
    private HomePageMenu menu;
    private UpperMenu upperMenu;
    private Contorller contorller;

    public HomePage() {
        super("Mapa Activation");
        contorller = new Contorller();
        setLayout(new BorderLayout()); //set BorderLayout

        //-- Creation of Left Side --//
        menu = new HomePageMenu();

        //-- Creation of Right side --//
        cal = new HomePageCalenderMenu();
        cal.setData(contorller.getSipActivaion());
        try {
            contorller.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            contorller.loadTheActivationSip();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cal.refresh();


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
    }

}
