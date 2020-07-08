package View;

import Controller.ActivationSipController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class HomePage extends JFrame {
    private HomePageCalenderMenu cal;
    private HomePageMenu menu;
    private UpperMenu upperMenu;
    private ActivationSipController activationSipController;

    public HomePage() {
        super("Mapa Activation");
        activationSipController = new ActivationSipController();
        setJMenuBar(createMenubar());
        setLayout(new BorderLayout()); //set BorderLayout

        //-- Creation of Left Side --//
        menu = new HomePageMenu();

        //-- Creation of Right side --//
        cal = new HomePageCalenderMenu();
        try {
            activationSipController.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            activationSipController.loadActivationSipToList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Home Page Create Calender I got Applications : " + activationSipController.getSipActivation().size());
        cal.setData(activationSipController);
        cal.refresh();

        menu.setDataToCalender(new GetDataFromSipListener() {
            @Override
            public void addActivation(FormEvent e) {
                activationSipController.addActivationSip(e);
                try {
                    activationSipController.connect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    activationSipController.insertingActivationSipToDataBase();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("Home Page Create menu set data I got Applications : " + activationSipController.getSipActivation().size());
                cal.refresh();
                activationSipController.disconnect();
            }

            @Override
            public void updateActivation(FormEvent ev) {
                activationSipController.updateActivationSip(ev);
                try {
                    activationSipController.connect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    activationSipController.updateActivationSipToDataBase(ActivationsMoves.FormId.getActivationId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                cal.refresh();
                activationSipController.disconnect();
            }

            @Override
            public void UpdateNumberRange() {
                try {
                    activationSipController.connect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    activationSipController.loadActivationSipToList();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                cal.refresh();
                activationSipController.disconnect();
            }
        });


        cal.setCalenderTableListener(new CalenderTableListener(){
            //-- Remove right click mouse activation --//
            @Override
            public void rowDelete(int row)
            {
                try {
                    activationSipController.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                activationSipController.removeActivation(row);
                cal.setData(activationSipController);
                System.out.println("Home Page setCalenderTableListener I got Applications : " + activationSipController.getSipActivation().size());
                activationSipController.disconnect();
            }
            //-- Set right click mouse first name to activation --//
            @Override
            public void addExpertUser(int row, String firstName) {
                activationSipController.addFirstNameToActivationList(row,firstName);
                System.out.println("Home Page addExpertUser I got Applications : " + activationSipController.getSipActivation().size());
                try {
                    activationSipController.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    activationSipController.updateUserExpertFirstName(row, firstName);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                cal.setData(activationSipController);
                activationSipController.disconnect();
            }

            @Override
            public void setStatus(String status,int row) {
                activationSipController.addStatusToActivationList(status,row);
                try {
                    activationSipController.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    activationSipController.updateStatus(status,row);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                cal.setData(activationSipController);
                System.out.println("Status is : "+ activationSipController.getSipActivation().get(row).getStatus());
                activationSipController.disconnect();
            }
        });

        //-- Creation of UpperMenu --//
        upperMenu = new UpperMenu("Welcome to Mapa Activation");

        //-- adding --//
        add(menu,BorderLayout.WEST);
        add(upperMenu,BorderLayout.NORTH);
        add(cal,BorderLayout.CENTER);

        //-- Frame Options -//
        setSize(1750, 750); // Size the Frame
        setMinimumSize(new Dimension(400, 500)); //set minimum
        setLocationRelativeTo(null); //Center the Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when i press X it will close
        setVisible(true); //show Frame
        activationSipController.disconnect();
    }
    private JMenuBar createMenubar() {
        JMenuBar menuBar = new JMenuBar(); // create the menu bar
        JMenu file = new JMenu("File"); //create the menu on the bar
        //-- Option in File Bar --//
        JMenuItem exit = new JMenuItem("Exit");
        file.addSeparator(); // add trans line to separate from other items
        file.add(exit);

        menuBar.add(file);

        //-- Set Mnemonic those are the Key you click on shortcuts , they are view as _ under the Menus like File --//
        file.setMnemonic(KeyEvent.VK_F);// this is a static class the makes VK_F as Key
        exit.setMnemonic(KeyEvent.VK_X);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(HomePage.this, "Do you really want to Exit?", "Confirm", JOptionPane.OK_OPTION,/*Change Icon*/JOptionPane.ERROR_MESSAGE);
                if (action == JOptionPane.OK_OPTION) {//action gets 0 for no 1 for ok
                        System.exit(0);
                }
            }
        });
        //--Set Accelerator those are the shortcut like Ctrl-x without using the menu--//
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));//for ctrl+X

        return menuBar;
    }
}
