package View;

import Model.DataBase;
import Model.Login;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class LoginUI extends JDialog {
    DataBase dataBase = new DataBase();
    private List<Login> db;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton OKButton;
    private JButton cancelButton;


    public LoginUI(JFrame parent) {
        super(parent,"Login Page" , true);
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        OKButton = new JButton("OK");
        cancelButton = new JButton("Cancel");


        try {
            dataBase.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataBase.load();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        getUsers();

        //-- Password --//
        passwordField.setEchoChar('*');//instead of circles we get ****


        // -- Action to OK Button -- //
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // this is is used to store Temp data not for DB
                String user = userField.getText();
                char[] pass = passwordField.getPassword();
                boolean flag = false;
                for (Login login : db) {
                    if (login.getUserName().equals(user) && login.getPassword().equals(new String(pass))){
                        JOptionPane.showMessageDialog(LoginUI.this,"Login Success","Login",JOptionPane.INFORMATION_MESSAGE);
                        flag = true;
                        dispose();
                    }
                }
                if(flag == false)
                    JOptionPane.showMessageDialog(LoginUI.this,"Login Failed","Login",JOptionPane.ERROR_MESSAGE);
            }
        });

        // -- Action to Cancel Button -- //
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        layoutControl();
        setSize(340,280);
        setLocationRelativeTo(parent);
    }

    private void getUsers(){
        dataBase.getUsers();
        db = dataBase.getUsers();
    }

    private void layoutControl (){
        JPanel controlPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        int sapce =20;
        Border titledBorder = BorderFactory.createTitledBorder("Login");
        Border spaceBorder = BorderFactory.createEmptyBorder(sapce,sapce,sapce,sapce);

        controlPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titledBorder));

        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill =  GridBagConstraints.NONE; //dont fill all Cell
        Insets rightPadding = new Insets(0,0,0,15);
        Insets noPadding = new Insets(0,0,0,0);

        //-- 1st Row --//
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=1;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlPanel.add(new JLabel("User Name : "),gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlPanel.add(userField,gc);
        //-- Next Row --//
        gc.gridy++;
        gc.gridx=0;
        gc.weightx=1;
        gc.weighty=1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlPanel.add(new JLabel("Password : "),gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlPanel.add(passwordField,gc);


        ///////// Buttons Panel /////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Dimension okBun = cancelButton.getPreferredSize();
        OKButton.setPreferredSize(okBun);
        buttonsPanel.add(OKButton);
        buttonsPanel.add(cancelButton);

        // add sub panels //
        setLayout(new BorderLayout());
        add(controlPanel,BorderLayout.CENTER);
        add(buttonsPanel,BorderLayout.SOUTH);


    }

}
