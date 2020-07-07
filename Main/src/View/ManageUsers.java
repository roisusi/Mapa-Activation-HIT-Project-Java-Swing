package View;

import Model.Users;
import Model.Login;
import Controller.UsersManagerController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

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

        cal.setData(controller.getUsers(), controller.getSystemUsers());
        cal.refresh();

        menu.setUsersToTable(new GetUserFromUsersListener() {
            @Override
            public void setUsers(Users user, Login login) throws SQLException {
                if(controller.isUserAlreadyExists(user) || controller.isLoginUserAlreadyExists(login))
                    JOptionPane.showMessageDialog(ManageUsers.this,"משתמש קיים במערכת","Error",JOptionPane.ERROR_MESSAGE);

                else
                {
                    try {
                        controller.connect();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    controller.insertingLoginUserToDataBase(login);
                    controller.insertingUserToDataBase(user, login.getId());
                    controller.addUserToUsersList(user);
                    controller.addLoginToLoginList(login);
                    cal.setData(controller.getUsers() ,controller.getSystemUsers());
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
                cal.setData(controller.getUsers(), controller.getSystemUsers());
                controller.disconnect();
            }

            //-- Edit right click mouse activation --//
            @Override
            public void rowEdit(List usersList, List loginList) throws SQLException {
                try {
                    controller.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                controller.updateSystemUsers(usersList);
                controller.updateLoginUsers(loginList);
                    /*int size = rowsList.size();

                    for (int i = 0; i < size; i++)
                    {
                        if((int)columnsList.get(i) >= 0 && (int)columnsList.get(i) <= 4)
                            controller.updateSystemUser(rowsList, columnsList, valuesList);
                        else
                            controller.updateLoginUser(rowsList, columnsList, valuesList);
                    }*/

                cal.setData(controller.getUsers(), controller.getSystemUsers());
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
