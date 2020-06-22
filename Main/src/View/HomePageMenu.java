package View;

import Controller.Controller;
import Model.Users;
import Model.UsersType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomePageMenu extends JPanel {

    public static class SessionId{
        private static String userName;
        private static String isApproved;

        public static String setUserName(String userName){
            return SessionId.userName = userName;
        }
        public static String getUserName(){
            return SessionId.userName;
        }
        public static String isApproved(String isApproved){
            return SessionId.isApproved = isApproved;
        }
        public static String isApproved(){
            return SessionId.isApproved;
        }
    }

    private JButton createForm;
    private JButton editForm;
    private JButton reports;
    private JButton manageUsers;
    private LoginUI loginUI;
    private JLabel userName;
    private JFrame parent;
    ActivationFormSIP activationFormSIPDialog;
    private Users user;
    private ManageUsers manageUsersForm;
    private Controller controller;
    private getDataFromSipListener getDataFromSipListener;

    public HomePageMenu() {

        // -- The 4 Buttons --//
        createForm = new JButton("צור הפעלת SIP");
        createForm.setPreferredSize(new Dimension(200,50));
        editForm = new JButton("ערוך הפעלה");
        editForm.setPreferredSize(new Dimension(200,50));
        reports = new JButton("דוחות");
        reports.setPreferredSize(new Dimension(200,50));
        manageUsers = new JButton("ניהול משתמשים");
        manageUsers.setPreferredSize(new Dimension(200,50));

        parent = new JFrame();
        loginUI = new LoginUI(parent);
        controller = new Controller();

        //-- Login -> get the logged user that logged --//
        loginUI.setUserLoggedListener(new UserLoggedListener() {
            @Override
            public void setUserFirstNameLogged(String User) {
                userName = new JLabel("שלום, " + User);
                SessionId.setUserName(User);
            }
        });
        loginUI.setVisible(true);

        try {
            controller.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.loadCalenderSipActivationToList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        user = controller.getUserFirstNameLogged();
        controller.disconnect();

        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(100,10,300,10);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Menu Option"); //adds Label to the border
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //-- Create Form Dialog --//
        activationFormSIPDialog = new ActivationFormSIP(HomePageMenu.this,0);

        //Grid Bag Layout - new way to set layouts
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        //-- 1St Row --/
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=1;
        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,30,0,30); // make space from label to field text
        userName.setFont(new Font("Arial",Font.PLAIN,36));
        userName.setForeground(Color.BLUE);
        add(userName,gc);

        //-- 2St Row -- //
        gc.gridy++;
        gc.weightx=1;
        gc.weighty=0.1;
        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,30,0,30); // make space from label to field text
        add(createForm,gc);
        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(editForm,gc);

        //-- 3St Row -- //
        gc.gridy++;
        gc.weightx=1;
        gc.weighty=2;
        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(reports,gc);
        gc.gridx=1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(manageUsers,gc);


        // -- the Creation of New Activation Sip -- //
        createForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                activationFormSIPDialog.setVisible(true);
            }

        });

        activationFormSIPDialog.setFormListener(new FormListener() {
            // ---- this Listener gets from Child Dialog the event of creating Activation Sip                         ---- //
            // ---- after the creation it adds the event to the DataBase, it send it to HomePage to show it on Table ----//
            @Override
            public void formEventOccurred(FormEvent e) {
                getDataFromSipListener.setActivation(e);
            }
        });

        editForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListOfActivationView listOfActivationView = new ListOfActivationView(parent,controller.getSipActivation());
                listOfActivationView.setVisible(true);
            }
        });

        // -- the Creation of Manage Users Form -- //
        manageUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(user.getUsersType().equals(UsersType.PrimaryManager)) {
                    manageUsersForm = ManageUsers.getInstance();
                    manageUsersForm.setVisible(true);
                }

                else
                    JOptionPane.showMessageDialog(HomePageMenu.this,"למשתמש זה אין הרשאות לניהול משתמשים","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    public void setDataToCalender(getDataFromSipListener getDataFromSipListener){
        this.getDataFromSipListener = getDataFromSipListener;
    }
}
