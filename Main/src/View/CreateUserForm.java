package View;

import Controller.UsersManagerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateUserForm extends JDialog {
    protected JTextField firstName;
    protected JTextField lastName;
    protected JTextField email;
    protected JTextField phoneNumber;
    protected JTextField type;
    protected JComboBox authenticationType;
    protected  JTextField userName;
    protected  JTextField password;
    private JButton buttonOK;
    private UsersManagerController usersManagerController;
    private CreateUserFormListener createUserFormListener;

    //-- Labels --//
    private JLabel welcome;
    private JLabel firstNameLabel = new JLabel("שם פרטי : ");
    private JLabel lastNameLabel = new JLabel("שם משפחה : ");
    private JLabel emailLabel = new JLabel("דואר אלקטרוני : ");
    private JLabel phoneNumberLabel = new JLabel("טלפון נייד : ");
    private JLabel typeLabel = new JLabel("סוג משתמש : ");
    private JLabel userNameLabel = new JLabel("שם משתמש : ");
    private JLabel passwordLabel = new JLabel("סיסמא : ");

    public CreateUserForm(JPanel parent) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("טופס משתמש חדש"));
        add(mainPanel,BorderLayout.CENTER);

        //-- Initialization --//
        firstName = new JTextField(15);
        lastName = new JTextField(15);
        email = new JTextField(15);
        phoneNumber = new JTextField(15);
        type = new JTextField(15);
        userName = new JTextField(15);
        password = new JTextField(15);
        welcome = new JLabel("טופס יצירת משתמש חדש");

        usersManagerController = new UsersManagerController();

        //-- ComboBox Configuration --//
        Dimension dim = new Dimension();
        dim.setSize(168,22);

        authenticationType = new JComboBox();
        DefaultComboBoxModel type = new DefaultComboBoxModel();
        type.addElement("נא לבחור");
        type.addElement("PrimaryManager");
        type.addElement("ProjectManager");
        type.addElement("Expert");
        authenticationType.setPreferredSize(dim);
        authenticationType.setModel(type);
        authenticationType.setSelectedIndex(0);

        //-- South Buttons --//
        buttonOK = new JButton("OK");

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFormToDataBase();
            }
        });

        FormControl();
        setModal(true);
        setSize(550, 500); // Size the Frame
        setLocationRelativeTo(parent);
    }

    public void setFormListener(CreateUserFormListener listener)
    {
        this.createUserFormListener = listener;
    }

    public void uploadFormToDataBase()
    {
        String firstNameEv;
        String lastNameEv;
        String emailEv;
        String phoneNumberEv;
        String typeEv;
        String userNameEv;
        String passwordEv;

        if (CheckEmptyCells()) {
            firstNameEv = firstName.getText();
            lastNameEv = lastName.getText();
            emailEv = email.getText();
            phoneNumberEv = phoneNumber.getText();
            typeEv = authenticationType.getSelectedItem().toString();
            userNameEv = userName.getText();
            passwordEv = password.getText();

            if(checkInput()) {
                try {
                    CreateFormEvent formEvent = new CreateFormEvent(firstNameEv, lastNameEv, emailEv, phoneNumberEv, typeEv, userNameEv, passwordEv);
                    createUserFormListener.formEventOccurred(formEvent);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                clearForm();
                dispose();
            }
            else
                JOptionPane.showMessageDialog(CreateUserForm.this, "אחד או יותר מהערכים אשר הוזנו אינו תקין", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(CreateUserForm.this,"נא השלם את הנתונים באדום","Error",JOptionPane.ERROR_MESSAGE);
    }

    private boolean checkInput()
    {
        boolean flag;
        boolean resultFlag= true;

        flag = usersManagerController.isString(firstName.getText(), firstName.getText().length());
        if (!flag) {
            firstNameLabel.setForeground(Color.red);
            resultFlag= false;
        }

        flag = usersManagerController.isString(lastName.getText(), lastName.getText().length());
        if (!flag) {
            lastNameLabel.setForeground(Color.red);
            resultFlag= false;
        }

        flag = usersManagerController.checkEmail(email.getText());
        if (!flag) {
            emailLabel.setForeground(Color.red);
            resultFlag= false;
        }

        flag = usersManagerController.checkPhoneNumber(phoneNumber.getText());
        if (!flag) {
            phoneNumberLabel.setForeground(Color.red);
            resultFlag= false;
        }
        return resultFlag;
    }

    private boolean CheckEmptyCells() {
        boolean flag = true;

        if (firstName.getText().isEmpty() || firstName.getText().trim().isEmpty()) {
            firstNameLabel.setForeground(Color.red);
            flag = false;
        }
        else {
            firstNameLabel.setForeground(Color.black);
        }

        if (lastName.getText().isEmpty() || lastName.getText().trim().isEmpty()) {
            lastNameLabel.setForeground(Color.red);
            flag = false;
        }
        else {
            lastNameLabel.setForeground(Color.black);
        }

        if (email.getText().isEmpty() || email.getText().trim().isEmpty()) {
            emailLabel.setForeground(Color.red);
            flag = false;
        }
        else {
            emailLabel.setForeground(Color.black);
        }

        if (phoneNumber.getText().isEmpty() || phoneNumber.getText().trim().isEmpty()) {
            phoneNumberLabel.setForeground(Color.red);
            flag = false;
        }
        else {
            phoneNumberLabel.setForeground(Color.black);
        }

        if (authenticationType.getSelectedIndex() == 0) {
            typeLabel.setForeground(Color.red);
            flag = false;
        }
        else {
            typeLabel.setForeground(Color.black);
        }

        if (userName.getText().isEmpty() || userName.getText().trim().isEmpty()) {
            userNameLabel.setForeground(Color.red);
            flag = false;
        }
        else {
            userNameLabel.setForeground(Color.black);
        }

        if (password.getText().isEmpty() || password.getText().trim().isEmpty()) {
            passwordLabel.setForeground(Color.red);
            flag = false;
        }
        else {
            passwordLabel.setForeground(Color.black);
        }
        return flag;
    }

    //-- Layout control panels --//
    private void FormControl() {
        JPanel formPanelTop = new JPanel();
        JPanel formPanelRight = new JPanel();
        JPanel buttonsPanel = new JPanel();

        //-- Form Panel --//
        formPanelRight.setLayout(new GridBagLayout());
        GridBagConstraints gcRight = new GridBagConstraints();
        gcRight.fill = GridBagConstraints.NONE;

        //----------------------------- Right Rows -----------------------------//
        gcRight.weighty=1;
        gcRight.weightx=1;
        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(firstName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,75);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(firstNameLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(lastName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(lastNameLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(email,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(emailLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(phoneNumber,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(phoneNumberLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(authenticationType,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(typeLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(userName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(userNameLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(password,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(passwordLabel,gcRight);

        //-- Buttons Panel --//

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(buttonOK);

        //-- Title Panel Top --//
        formPanelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        formPanelTop.setBorder(BorderFactory.createLineBorder(Color.black));
        welcome.setFont(new Font("Arial",Font.PLAIN,36));
        formPanelTop.add(welcome);

        // Add sub panels //
        setLayout(new BorderLayout());
        add(formPanelTop,BorderLayout.NORTH);
        add(formPanelRight,BorderLayout.EAST);
        add(buttonsPanel,BorderLayout.SOUTH);
    }

    private void clearForm() {
        firstName.setText("");
        firstNameLabel.setForeground(Color.black);
        lastName.setText("");
        lastNameLabel.setForeground(Color.black);
        email.setText("");
        emailLabel.setForeground(Color.black);
        phoneNumber.setText("");
        phoneNumberLabel.setForeground(Color.black);
        authenticationType.setSelectedIndex(0);
        typeLabel.setForeground(Color.black);
        userName.setText("");
        userNameLabel.setForeground(Color.black);
        password.setText("");
        passwordLabel.setForeground(Color.black);
    }
}
