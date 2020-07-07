package View;

import Controller.UsersManagerController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginUI extends JDialog {
    private UsersManagerController controller;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton OKButton;
    private JButton cancelButton;
    private UserLoggedListener userLoggedListener;

    public LoginUI() {
    }

    public LoginUI(JFrame parent) {
        super(parent,"Login Page" , true);
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        OKButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        controller = new UsersManagerController();


        //-- Password --//
        passwordField.setEchoChar('*');//instead of circles we get ****

        //-- Action to OK Button --//
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                //-- Create first Data Base instance --//
                try {
                    controller.connect();
                } catch (Exception ev) {
                    ev.printStackTrace();
                }

                try {
                    controller.loadUsersFromDataBaseToList();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    controller.loadSystemUsersFromDataBaseToList();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if(controller.loginUserAuthentication(username, password))
                {
                    JOptionPane.showMessageDialog(LoginUI.this,"Login Success","Login",JOptionPane.INFORMATION_MESSAGE);
                    controller.loadLoggedUser(controller.getLoginUser().getId());
                    userLoggedListener.setUserFirstNameLogged(controller.getUserFirstNameLogged().getFirstName());
                    dispose();
                }

                else
                    JOptionPane.showMessageDialog(LoginUI.this,"Login Failed","Login",JOptionPane.ERROR_MESSAGE);
                    //JOptionPane.showMessageDialog(LoginUI.this,"Illegal Input","Login",JOptionPane.ERROR_MESSAGE);
            }
        });

        controller.disconnect();
        // -- Action to Cancel Button -- //
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        layoutControl();
        setSize(400,300);
        setLocationRelativeTo(parent);
    }

    public void setUserLoggedListener(UserLoggedListener listener)
    {
        this.userLoggedListener = listener;
    }

    private void layoutControl (){
        JPanel controlPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        int sapce =10;

        Border spaceBorder = BorderFactory.createEmptyBorder(sapce,sapce,sapce,sapce);
        Border p = BorderFactory.createLineBorder(Color.BLACK);
        Border titledBorder = BorderFactory.createTitledBorder(p,"Login");
        controlPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titledBorder));

        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill =  GridBagConstraints.NONE; //dont fill all Cell
        Insets rightPadding = new Insets(0,0,0,15);
        Insets noPadding = new Insets(0,0,0,0);
        Insets upPadding = new Insets(0,0,40,0);
        Insets downPadding = new Insets(20,0,0,0);

        //-- 1st Row --//
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=1;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(20,0,0,15);
        controlPanel.add(new JLabel("User Name : "),gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = downPadding;
        controlPanel.add(userField,gc);
        //-- Next Row --//
        gc.gridy++;
        gc.gridx=0;
        gc.weightx=1;
        gc.weighty=1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0,0,40,15);
        controlPanel.add(new JLabel("Password : "),gc);
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = upPadding;
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
