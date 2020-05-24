package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivationFormSIP extends JDialog {
    protected JTextField customerID;
    protected JTextField customerName;
    protected JTextField contactName;
    protected JTextField customerPhoneNumber;
    protected JTextField customerEmail;
    protected JTextField customerTechName;
    protected JTextField customerTechPhoneNumber;
    protected JTextField pbxType;
    protected JComboBox connectionType;
    protected JTextField infrastructure;
    protected JRadioButton routerTypeYES;
    protected JRadioButton routerTypeNO;
    protected ButtonGroup routerTypeGroup;
    protected JTextField routerTypeTextField;
    protected JComboBox CODEC;
    protected JComboBox typeOfCalls;
    protected JComboBox identificationType;
    protected JTextField totalNumbers;
    protected JTextField totalCalls;
    protected JTextField snbNumber;
    protected JTextField numberRange;
    protected JTextField wanAddressA;
    protected JTextField wanAddressB;
    protected JTextField wanAddressC;
    protected JTextField wanAddressD;
    protected JTextField lanAddressA;
    protected JTextField lanAddressB;
    protected JTextField lanAddressC;
    protected JTextField lanAddressD;
    protected JTextField ipAddressA;
    protected JTextField ipAddressB;
    protected JTextField ipAddressC;
    protected JTextField ipAddressD;
    protected JTextField internetUser;
    protected JComboBox signalAddress;
    protected JComboBox mediaAddress;
    protected JTextField areaCode;
    protected JTextField emergencyCity;
    protected JButton callOutSideCountry;
    protected JTextField crNumber;
    protected JTextField trunkNumber;
    protected JSpinner sbcPort;

    private JButton addToSchedule;


    public ActivationFormSIP(JPanel parent) {
        //-- Initialization --//
        customerID = new JTextField(15);
        customerName = new JTextField(15);
        contactName = new JTextField(15);
        customerPhoneNumber = new JTextField(15);
        customerEmail = new JTextField(15);
        customerTechName = new JTextField(15);
        customerTechPhoneNumber = new JTextField(15);
        pbxType = new JTextField(15);
        infrastructure = new JTextField(15);
        totalNumbers = new JTextField(15);
        totalCalls = new JTextField(15);
        routerTypeTextField = new JTextField(15);


        //-- ComboBox Configuration --//
        Dimension dim = new Dimension();
        dim.setSize(153,22);

        connectionType = new JComboBox();
        DefaultComboBoxModel connectionTypeCb = new DefaultComboBoxModel();
        connectionTypeCb.addElement("ADSL");
        connectionTypeCb.addElement("HOT");
        connectionType.setPreferredSize(dim);
        connectionType.setModel(connectionTypeCb);
        connectionType.setSelectedIndex(0);

        CODEC = new JComboBox();
        DefaultComboBoxModel CODECCb = new DefaultComboBoxModel();
        CODECCb.addElement("711A");
        CODECCb.addElement("711U");
        CODECCb.addElement("711A and 711U");
        CODECCb.addElement("729");
        CODECCb.addElement("Default - All CODECs");
        CODEC.setPreferredSize(dim);
        CODEC.setModel(CODECCb);
        CODEC.setSelectedIndex(4);

        typeOfCalls = new JComboBox();
        DefaultComboBoxModel typeOfCallsCb = new DefaultComboBoxModel();
        typeOfCallsCb.addElement("נכנסות");
        typeOfCallsCb.addElement("יוצאות");
        typeOfCallsCb.addElement("נכנסות + יוצאות");
        typeOfCalls.setPreferredSize(dim);
        typeOfCalls.setModel(typeOfCallsCb);
        typeOfCalls.setSelectedIndex(2);

        identificationType = new JComboBox();
        DefaultComboBoxModel identificationTypeCb = new DefaultComboBoxModel();
        identificationTypeCb.addElement("שלוחה");
        identificationTypeCb.addElement("מוביל");
        identificationTypeCb.addElement("חסוי");
        identificationType.setPreferredSize(dim);
        identificationType.setModel(identificationTypeCb);
        identificationType.setSelectedIndex(0);


        //-- Radio Button Configuration --//
        routerTypeYES = new JRadioButton("כן");
        routerTypeYES.setHorizontalTextPosition(SwingConstants.LEFT);
        routerTypeNO = new JRadioButton("לא");
        routerTypeNO.setHorizontalTextPosition(SwingConstants.LEFT);
        routerTypeGroup = new ButtonGroup();
        routerTypeGroup.add(routerTypeNO);
        routerTypeGroup.add(routerTypeYES);
        routerTypeYES.setActionCommand("YES");//case yes
        routerTypeNO.setActionCommand("NO");//case No
        routerTypeNO.setSelected(true);
        routerTypeTextField.setEnabled(false);

        //-- South Buttons --//
        addToSchedule = new JButton("Add To Schedule");



        //-------------------------------- Listeners --------------------------------//

        //--------- Radio Yes Click ---------//
        routerTypeYES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routerTypeTextField.setEnabled(true);
            }
        });
        //--------- Radio No Click ---------//
        routerTypeNO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routerTypeTextField.setEnabled(false);
            }
        });


        FormControl();
        setSize(800, 600); // Size the Frame
        //setLocation(300,600); //Center the Frame
        setLocationRelativeTo(parent);
    }

    //-- Layout control panels --//
    private void FormControl () {
        JPanel formPanelLeft = new JPanel();
        JPanel formPanelRight = new JPanel();
        JPanel buttonsPanel = new JPanel();

        //-- Form Panel --//
        formPanelLeft.setLayout(new GridBagLayout());
        formPanelLeft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridBagConstraints gcLeft = new GridBagConstraints();
        gcLeft.fill = GridBagConstraints.NONE;

        formPanelRight.setLayout(new GridBagLayout());
        formPanelRight.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridBagConstraints gcRight = new GridBagConstraints();
        gcRight.fill = GridBagConstraints.NONE;



        //-- Left Rows --//

        gcLeft.gridy = 0;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,100,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(identificationType,gcRight);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(new JLabel("אופן כניסת שיחה : "),gcRight);


        //-- Right Rows --//

        gcRight.weighty=1;
        gcRight.weightx=1;
        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerID,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("מספר לקוח : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("שם לקוח : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(contactName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("שם איש קשר : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerPhoneNumber,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("טלפון נייד : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerEmail,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("דואר אלקטרוני : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerTechName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("איש קשר טכני : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerTechPhoneNumber,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("טלפון נייד : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(pbxType,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("סוג מרכזייה : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(connectionType,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("סוג קישור : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(infrastructure,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("רוחב פס : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(totalNumbers,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("כמות מספרים : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(totalCalls,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("כמות שיחות בו זמנית : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(routerTypeNO,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("נתב בניהולנו ? : "),gcRight);
        gcRight.gridy++;
        gcRight.gridx=0;
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(routerTypeYES,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(routerTypeTextField,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("סוג הנתב : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(CODEC,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("קידוד : "),gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(typeOfCalls,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(new JLabel("אופן כניסת שיחה : "),gcRight);


        //--Buttons Panel --//

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(addToSchedule);


        // Add sub panels //
        setLayout(new BorderLayout());
        add(formPanelLeft,BorderLayout.CENTER);
        add(formPanelRight,BorderLayout.EAST);
        add(buttonsPanel,BorderLayout.SOUTH);

    }
}
