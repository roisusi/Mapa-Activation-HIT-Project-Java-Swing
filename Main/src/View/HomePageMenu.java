package View;

import Controller.Contorller;
import Model.Login;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageMenu extends JPanel {

    private JButton createForm;
    private JButton editForm;
    private JButton reports;
    private JButton manageUsers;
    private JLabel welcomUser;
    private LoginUI loginUI;
    private JLabel userName;
    private JFrame parent;
    ActivationFormSIP activationFormSIPDialog;
    private Contorller contorller;
    private HomePageCalenderMenu homePageCalenderMenu= new HomePageCalenderMenu();




    public HomePageMenu() {

        // -- The 4 Buttons --//
        createForm = new JButton("Create Activation");
        createForm.setPreferredSize(new Dimension(200,50));
        editForm = new JButton("Edit Activation");
        editForm.setPreferredSize(new Dimension(200,50));
        reports = new JButton("Reports");
        reports.setPreferredSize(new Dimension(200,50));
        manageUsers = new JButton("Manage Users");
        manageUsers.setPreferredSize(new Dimension(200,50));

        parent = new JFrame();
        loginUI = new LoginUI(parent);
        contorller = new Contorller();


        //-- Login Popup Dialog --//
        loginUI.setVisible(true);
        loginUI.setGetUserLogged(new GetUserLogged() {
            @Override
            public void getUser(String User) {
                userName = new JLabel("Hello, " + User);
            }
        });

        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(100,10,300,10);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Menu Option"); //adds Label to the border
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //-- Create Form Dialog --//
        activationFormSIPDialog = new ActivationFormSIP(HomePageMenu.this);
        activationFormSIPDialog.setFormListener(new FormListener() {
            @Override
            public void formEventOccurred(FormEvent e) {
                contorller.addActivationSip(e);
                homePageCalenderMenu.setData(contorller.getSipActivaion());
                System.out.println(contorller.getSipActivaion().size());
            }
        });

        editForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(contorller.getSipActivaion().size());
            }
        });

        //Grid Bag Layout - new way to set layouts
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE; //if the component isnt the same size as the frame , it resize it. NONE make it non resize

        //-- 1St Row --/
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=1;
        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
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

        createForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                activationFormSIPDialog.setVisible(true);
            }

        });

    }

}
