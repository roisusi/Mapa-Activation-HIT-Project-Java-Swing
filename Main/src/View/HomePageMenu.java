package View;

import Controller.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomePageMenu extends JPanel {

    private JButton createForm;
    private JButton editForm;
    private JButton reports;
    private JButton manageUsers;
    private LoginUI loginUI;
    private JLabel userName;
    private JFrame parent;
    private ActivationFormSIP activationFormSIPDialog;
    private ManageUsers manageUsersForm;
    private ReportView reportsForm;
    private ActivationSipController activationSipController;
    private UsersManagerController usersManagerController;
    private NumberRangeController numberRangeController;
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
        activationSipController = new ActivationSipController();
        usersManagerController = new UsersManagerController();
        numberRangeController = new NumberRangeController();

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
            activationSipController.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            activationSipController.loadActivationSipToList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        activationSipController.disconnect();

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
                if(usersManagerController.getUserFirstNameLogged().createForm())
                {
                    if (ActivationsMoves.SessionId.getFromRange() != null && ActivationsMoves.SessionId.getToRange() != null)
                        ActivationsMoves.SessionId.remove();
                    activationFormSIPDialog.failActivation.setEnabled(false);
                    activationFormSIPDialog.activationToFile.setEnabled(false);
                    activationFormSIPDialog.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(HomePageMenu.this, "למשתמש זה אין הרשאות ליצירת טופס התקנה", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        editForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usersManagerController.getUserFirstNameLogged().editForm())
                {
                    if (ActivationsMoves.SessionId.getFromRange() != null && ActivationsMoves.SessionId.getToRange() != null)
                        ActivationsMoves.SessionId.remove();
                    listOfActivationView.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(HomePageMenu.this, "למשתמש זה אין הרשאות לעריכת טופס התקנה", "Error", JOptionPane.ERROR_MESSAGE);
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
                numberRangeController.addNumberRange(e);
                try {
                    activationSipController.connect();
                    numberRangeController.connect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    numberRangeController.insertingNumberRangeToDataBase();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                activationSipController.disconnect();
            }

            @Override
            public void formUpdateFails() {
                getDataFromSipListener.UpdateNumberRange();
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
            public void UpdateNumberRange() {
                getDataFromSipListener.UpdateNumberRange();
            }
        });

        // -- the Creation of Manage Users Form -- //
        manageUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(usersManagerController.getUserFirstNameLogged().manageUsers()) {
                    manageUsersForm = ManageUsers.getInstance();
                    manageUsersForm.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(HomePageMenu.this,"למשתמש זה אין הרשאות לניהול משתמשים","Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(usersManagerController.getUserFirstNameLogged().reports())
                    reportsForm.setVisible(true);
                else
                    JOptionPane.showMessageDialog(HomePageMenu.this, "למשתמש זה אין הרשאות לקבלת דוחות", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void setDataToCalender(GetDataFromSipListener getDataFromSipListener){
        this.getDataFromSipListener = getDataFromSipListener;
    }
}