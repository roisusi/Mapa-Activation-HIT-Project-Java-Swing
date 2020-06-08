package View;


import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class HomePage extends JFrame {
    private HomePageCalenderMenu cal;
    private HomePageMenu menu;
    private UpperMenu upperMenu;
    private Controller controller;

    public HomePage() {
        super("Mapa Activation");
        controller = new Controller();
        setLayout(new BorderLayout()); //set BorderLayout

        //-- Creation of Left Side --//
        menu = new HomePageMenu();

        //-- Creation of Right side --//
        cal = new HomePageCalenderMenu();
        try {
            controller.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.loadCalenderSipActivationToList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Home Page Create Calender I got Applications : " + controller.getSipActivation().size());
        cal.setData(controller.getSipActivation());
        cal.refresh();

        menu.setDataToCalender(new getDataFromSipListener() {
            @Override
            public void setActivation(FormEvent e) {
                controller.addActivationSip(e);
                try {
                    controller.connect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    controller.insertingActivationSipToDataBase();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("Home Page Create menu set data I got Applications : " + controller.getSipActivation().size());
                cal.refresh();
                controller.disconnect();
            }
        });
        cal.setCalenderTableListener(new CalenderTableListener(){
            //-- Remove right click mouse activation --//
            @Override
            public void rowDelete(int row)
            {
                try {
                    controller.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                controller.removeActivation(row);
                cal.setData(controller.getSipActivation());
                System.out.println("Home Page setCalenderTableListener I got Applications : " + controller.getSipActivation().size());
                controller.disconnect();
            }
            //-- Set right click mouse first name to activation --//
            @Override
            public void addExpertUser(int row, String firstName) {
                controller.addFirstNameToActivationList(row,firstName);
                System.out.println("Home Page addExpertUser I got Applications : " + controller.getSipActivation().size());
                try {
                    controller.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    controller.updateUserExpertFirstName(row, firstName);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                cal.setData(controller.getSipActivation());
                controller.disconnect();
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
