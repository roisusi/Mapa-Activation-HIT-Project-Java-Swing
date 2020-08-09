package View;

import Controller.ActivationSipController;
import Controller.UsersManagerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class HomePage extends JFrame {
    private HomePageCalenderMenu cal;
    private HomePageMenu menu;
    private UpperMenu upperMenu;
    private ActivationSipController activationSipController;
    private UsersManagerController usersManagerController;

    public HomePage() {
        super("Mapa Activation");
        activationSipController = new ActivationSipController();
        usersManagerController = new UsersManagerController();
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
                try {
                    activationSipController.removeActivation(row);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cal.setData(activationSipController);
                activationSipController.disconnect();
            }
            //-- Set right click mouse first name to activation --//
            @Override
            public void addExpertUser(int row, String firstName) {
                activationSipController.addFirstNameToActivationList(row,firstName);
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
                activationSipController.disconnect();
            }
        });

        //-- Creation of UpperMenu --//
        upperMenu = new UpperMenu("מערכת ניהול הפעלות");

        //-- adding --//
        add(menu,BorderLayout.EAST);
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
        JMenu file = new JMenu("קובץ"); //create the menu on the bar

        //-- Option in File Bar --//
        JMenuItem disconnect = new JMenuItem("התנתק");
        JMenuItem exit = new JMenuItem("יציאה");

        //-- Align Menu To right --//
        menuBar.add(Box.createHorizontalGlue());
        file.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        exit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        disconnect.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        file.add(disconnect);
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


        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LoginUI loginUI = new LoginUI((JFrame) SwingUtilities.getRootPane(HomePage.super.rootPane).getParent());
                dispose();
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        });

        return menuBar;
    }
}
