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
import java.util.ArrayList;


public class HomePageMenu extends JPanel {



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
    private ReportView reportsForm;
    private Controller controller;
    private GetDataFromSipListener getDataFromSipListener;
    private ListOfActivationView listOfActivationView;


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
                ActivationsMoves.SessionId.setUserName(User);
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
        activationFormSIPDialog = new ActivationFormSIP(HomePageMenu.this,0,0);
        //-- Edit Form Dialog --//
        listOfActivationView = new ListOfActivationView(parent);//,controller.getSipActivation());
        //-- Reports Form Dialog --//
        reportsForm = new ReportView(HomePageMenu.this);

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
                if (ActivationsMoves.SessionId.getFromRange() != null && ActivationsMoves.SessionId.getToRange() != null)
                    ActivationsMoves.SessionId.remove();
                activationFormSIPDialog.failActivation.setEnabled(false);
                activationFormSIPDialog.setVisible(true);
            }

        });

        editForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ActivationsMoves.SessionId.getFromRange() != null && ActivationsMoves.SessionId.getToRange() != null)
                    ActivationsMoves.SessionId.remove();
                listOfActivationView.setVisible(true);
            }
        });

        activationFormSIPDialog.setFormListener(new FormListener() {
            // ---- this Listener gets from Child Dialog the event of creating Activation Sip                       ---- //
            // ---- after the creation it adds the event to the DataBase, it send it to HomePage to show it on Table ----//
            @Override
            public void formEventOccurred(FormEvent e) {
                getDataFromSipListener.addActivation(e);
            }
            @Override
            public void formEventOccurredNumber(FormEvent e) {
                controller.addNumberRange(e);
                try {
                    controller.connect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    controller.insertingNumberRangeToDataBase();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            controller.disconnect();
            }

            @Override
            public void formUpdateFails() {
                getDataFromSipListener.Update();
            }
        });

        //-- Move Data From Edit to Calender --//
        listOfActivationView.setDataFromSipListener(new GetDataFromSipListener() {
            @Override
            public void addActivation(FormEvent e) {
            //Leave Empty
            }

            @Override
            public void updateActivation(FormEvent e) {
                getDataFromSipListener.updateActivation(e);
            }

            @Override
            public void Update() {
                getDataFromSipListener.Update();
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

        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportsForm.setVisible(true);
            }
        });
    }

    public void setDataToCalender(GetDataFromSipListener getDataFromSipListener){
        this.getDataFromSipListener = getDataFromSipListener;
    }
}
