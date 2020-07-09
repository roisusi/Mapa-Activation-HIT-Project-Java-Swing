package View;

import Controller.UsersManagerController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ManageUsers extends JFrame {
    private static ManageUsers single_instance = null;
    private ManageUsersTableMenu cal;
    private ManageUsersMenu menu;
    private UpperMenu upperMenu;
    private UsersManagerController controller;

    private ManageUsers() {
        super("ניהול משתמשים");
        controller = new UsersManagerController();
        setLayout(new BorderLayout()); //set BorderLayout

        //-- Creation of Left Side --//
        menu = new ManageUsersMenu();

        //-- Creation of Right side --//
        cal = new ManageUsersTableMenu();

        try {
            controller.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.loadSystemUsersFromDataBaseToList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            controller.loadUsersFromDataBaseToList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cal.setData(controller);
        cal.refresh();

        menu.setUsersToTable(new GetUserFromUsersListener() {
            @Override
            public void setUsers(CreateFormEvent e) throws SQLException {
                controller.createUser(e);
                if(controller.isUserAlreadyExists() || controller.isLoginUserAlreadyExists())
                    JOptionPane.showMessageDialog(ManageUsers.this,"משתמש קיים במערכת","Error",JOptionPane.ERROR_MESSAGE);

                else
                {
                    try {
                        controller.connect();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    controller.insertingLoginUserToDataBase();
                    controller.insertingUserToDataBase();
                    controller.addUserToUsersList();
                    controller.addLoginToLoginList();
                    cal.setData(controller);
                    cal.refresh();
                    controller.disconnect();
                }
            }
        });

        cal.setUsersTableListener(new UsersTableListener(){
            //-- Remove right click mouse activation --//
            @Override
            public void rowDelete(int row)
            {
                try {
                    controller.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                controller.removeUser(row);
                cal.setData(controller);
                controller.disconnect();
            }

            //-- Edit right click mouse activation --//
            @Override
            public void rowEdit() throws SQLException {
                try {
                    controller.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                controller.updateSystemUsers();
                controller.updateLoginUsers();
                cal.setData(controller);
                controller.disconnect();
            }
        });

        //-- Creation of UpperMenu --//
        upperMenu = new UpperMenu("ברוכים הבאים לניהול משתמשים");

        //-- adding --//
        add(menu,BorderLayout.WEST);
        add(upperMenu,BorderLayout.NORTH);
        add(cal,BorderLayout.CENTER);

        //-- Frame Options -//
        setSize(1400, 800); // Size the Frame
        setMinimumSize(new Dimension(400, 500)); //set minimum
        setLocationRelativeTo(null); //Center the Frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //when i press X it will close
        setVisible(true); //show Frame
        controller.disconnect();
    }

    public static ManageUsers getInstance(){
        if(single_instance == null)
            single_instance = new ManageUsers();

        return single_instance;
    }
}
