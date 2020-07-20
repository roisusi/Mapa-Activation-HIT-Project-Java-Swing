package View;

import Controller.*;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivationFormSIP extends JDialog {
    private JPanel formPanelTop = new JPanel();
    private JPanel formPanelLeft = new JPanel();
    private JPanel formPanelRight = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    // JTextField //
    protected JTextField customerID;
    protected JTextField customerName;
    protected JTextField contactName;
    protected JTextField customerPhoneNumber;
    protected JTextField customerEmail;
    protected JTextField customerTechName;
    protected JTextField customerTechPhoneNumber;
    protected JTextField pbxType;
    protected JTextField infrastructure;
    protected JTextField routerTypeTextField;
    protected JTextField totalNumbers;
    protected JTextField totalCalls;
    protected JTextField snbNumber;
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
    protected JTextField emergencyCity;
    protected JTextField crNumber;
    protected JTextField trunkNumber;

    // Radio Buttons //
    protected ButtonGroup callOutSideCountry;
    protected JRadioButton callOutSideCountryYES;
    protected JRadioButton callOutSideCountryNO;
    protected ButtonGroup routerTypeGroup;
    protected JRadioButton routerTypeYES;
    protected JRadioButton routerTypeNO;

    // Spinners
    protected JSpinner sbcPort;


    // Observers //
    private FormListener formListener;

    // ComboBox //
    protected JComboBox connectionType;
    protected JComboBox signalAddress;
    protected JComboBox mediaAddress;
    protected JComboBox areaCode;
    protected JComboBox CODEC;
    protected JComboBox typeOfCalls;
    protected JComboBox identificationType;

    //-- Labels --//
    private JLabel wellcome;
    private JLabel customerIDLabel = new JLabel("מספר לקוח : ");
    private JLabel customerNameLabel = new JLabel("שם לקוח : ");
    private JLabel contactNameLabel = new JLabel("שם איש קשר : ");
    private JLabel customerPhoneNumberLabel = new JLabel("טלפון נייד : ");
    private JLabel customerEmailLabel = new JLabel("דואר אלקטרוני : ");
    private JLabel customerTechNameLabel = new JLabel("איש קשר טכני : ");
    private JLabel customerTechPhoneNumberLabel = new JLabel("טלפון נייד : ");
    private JLabel pbxTypeLabel = new JLabel("סוג מרכזייה : ");
    private JLabel connectionTypeLabel = new JLabel("סוג קישור : ");
    private JLabel infrastructureLabel = new JLabel("רוחב פס : ");
    private JLabel totalNumbersLabel = new JLabel("כמות מספרים : ");
    private JLabel totalCallsLabel = new JLabel("כמות שיחות בו זמנית : ");
    private JLabel routerTypeLabel = new JLabel("סוג הנתב : ");
    private JLabel CODECLabel = new JLabel("קידוד : ");
    private JLabel typeOfCallsLabel = new JLabel("אופן כניסת שיחה : ");
    private JLabel internetUserLabel = new JLabel("שם משתמש User : ");
    private JLabel identificationTypeLabel = new JLabel("זיהוי שיחה : ");
    private JLabel snbNumberLabel = new JLabel("מספר מוביל : ");
    private JLabel numberRangeLabel = new JLabel("טווח מספרים : ");
    private JLabel wanAddressLabel = new JLabel("כתובת WAN : ");
    private JLabel lanAddressLabel = new JLabel("כתובת LAN : ");
    private JLabel ipAddressLabel = new JLabel("כתובת IP במתג : ");
    private JLabel signalAddressLabel = new JLabel("כתובת Signal : ");
    private JLabel mediaAddressLabel = new JLabel("כתובת Media : ");
    private JLabel areaCodeLabel = new JLabel("איזור חיוג : ");
    private JLabel emergencyCityLabel = new JLabel("עיר חירום : ");
    private JLabel callOutSideCountryLabel = new JLabel("חיוג לחול : ");
    private JLabel crNumberLabel = new JLabel("מספר תקרית : ");
    private JLabel trunkNumberLabel = new JLabel("שם אלומה : ");
    private JLabel sbcPortLabel = new JLabel("פורט SBC : ");
    private JLabel dateLabel = new JLabel("תאריך : ");
    private JLabel fireWallLabel = new JLabel("נתב בניהולנו ? : ");

    // Buttons //
    protected JButton editToSchedule;
    protected JButton failActivation;
    protected JButton activationToFile;
    protected JButton numberRangeButton;
    protected JButton templateActivaion;
    private JButton addToSchedule;
    private JButton FNR;

    // Controllers //
    private ActivationSipController activationSipController;
    private UsersManagerController usersManagerController;

    // DatePicker //
    private String datePickerEv;
    private DateLabelFormatter dateLabelFormatter;
    protected JDatePicker datePicker;

    // Other Forms //
    private NumberRangesView numberRangesView;
    private LoginUI loginUI;

    private int indexOfButton;

    public ActivationFormSIP(JPanel parent, int indexOfButton, int activationId) {

        //-----------------------------------------------------//
        //--------------- Start of Initialization -------------//
        //-----------------------------------------------------//
        JPanel mainPanel = new JPanel();

        // TextField //
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
        snbNumber = new JTextField(15);
        internetUser = new JTextField(15);
        emergencyCity = new JTextField(15);
        crNumber = new JTextField(15);
        trunkNumber = new JTextField(15);
        wellcome = new JLabel("טופס הפעלת SIP");
        this.indexOfButton = indexOfButton;

        // Controller //
        activationSipController = new ActivationSipController();
        usersManagerController = new UsersManagerController();
        try {
            usersManagerController.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            usersManagerController.loadUsersFromDataBaseToList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        usersManagerController.disconnect();

        //-- Spinner --//
        sbcPort = new JSpinner();
        sbcPort.setValue(7500);

        //-- IP Addresses --//
        wanAddressA = new JTextField(3);
        wanAddressB = new JTextField(3);
        wanAddressC = new JTextField(3);
        wanAddressD = new JTextField(3);

        lanAddressA = new JTextField(3);
        lanAddressB = new JTextField(3);
        lanAddressC = new JTextField(3);
        lanAddressD = new JTextField(3);

        ipAddressA = new JTextField(3);
        ipAddressB = new JTextField(3);
        ipAddressC = new JTextField(3);
        ipAddressD = new JTextField(3);


        //-- ComboBox Configuration --//
        Dimension dim = new Dimension();
        dim.setSize(168,22);

        connectionType = new JComboBox();
        DefaultComboBoxModel connectionTypeCb = new DefaultComboBoxModel();
        connectionTypeCb.addElement("נא לבחור");
        connectionTypeCb.addElement("ADSL");
        connectionTypeCb.addElement("HOT");
        connectionType.setPreferredSize(dim);
        connectionType.setModel(connectionTypeCb);
        connectionType.setSelectedIndex(0);

        CODEC = new JComboBox();
        DefaultComboBoxModel CODECCb = new DefaultComboBoxModel();
        CODECCb.addElement("נא לבחור");
        CODECCb.addElement("711A");
        CODECCb.addElement("711U");
        CODECCb.addElement("711A and 711U");
        CODECCb.addElement("729");
        CODECCb.addElement("Default - All CODECs");
        CODEC.setPreferredSize(dim);
        CODEC.setModel(CODECCb);
        CODEC.setSelectedIndex(0);

        typeOfCalls = new JComboBox();
        DefaultComboBoxModel typeOfCallsCb = new DefaultComboBoxModel();
        typeOfCallsCb.addElement("נא לבחור");
        typeOfCallsCb.addElement("נכנסות");
        typeOfCallsCb.addElement("יוצאות");
        typeOfCallsCb.addElement("נכנסות + יוצאות");
        typeOfCalls.setPreferredSize(dim);
        typeOfCalls.setModel(typeOfCallsCb);
        typeOfCalls.setSelectedIndex(0);

        identificationType = new JComboBox();
        DefaultComboBoxModel identificationTypeCb = new DefaultComboBoxModel();
        identificationTypeCb.addElement("נא לבחור");
        identificationTypeCb.addElement("שלוחה");
        identificationTypeCb.addElement("מוביל");
        identificationTypeCb.addElement("חסוי");
        identificationType.setPreferredSize(dim);
        identificationType.setModel(identificationTypeCb);
        identificationType.setSelectedIndex(0);

        signalAddress = new JComboBox();
        DefaultComboBoxModel signalAddressCb = new DefaultComboBoxModel();
        signalAddressCb.addElement("נא לבחור");
        signalAddressCb.addElement("212.199.157.154");
        signalAddressCb.addElement("80.179.122.138");
        signalAddressCb.addElement("212.199.156.202");
        signalAddressCb.addElement("212.199.220.21");
        signalAddress.setPreferredSize(dim);
        signalAddress.setModel(signalAddressCb);


        mediaAddress = new JComboBox();
        DefaultComboBoxModel mediaAddressCb = new DefaultComboBoxModel();
        mediaAddressCb.addElement("נא לבחור");
        mediaAddressCb.addElement("212.199.157.155");
        mediaAddressCb.addElement("80.179.122.137");
        mediaAddressCb.addElement("212.199.156.201");
        mediaAddressCb.addElement("212.199.220.22");
        mediaAddress.setPreferredSize(dim);
        mediaAddress.setModel(mediaAddressCb);
        mediaAddress.setSelectedIndex(0);

        areaCode = new JComboBox();
        DefaultComboBoxModel areaCodeCb = new DefaultComboBoxModel();
        areaCodeCb.addElement("נא לבחור");
        areaCodeCb.addElement("02");
        areaCodeCb.addElement("03");
        areaCodeCb.addElement("04");
        areaCodeCb.addElement("08");
        areaCodeCb.addElement("09");
        areaCode.setPreferredSize(dim);
        areaCode.setModel(areaCodeCb);
        areaCode.setSelectedIndex(0);


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

        callOutSideCountryYES = new JRadioButton("כן");
        callOutSideCountryYES.setHorizontalTextPosition(SwingConstants.LEFT);
        callOutSideCountryNO = new JRadioButton("לא");
        callOutSideCountryNO.setHorizontalTextPosition(SwingConstants.LEFT);
        callOutSideCountry = new ButtonGroup();
        callOutSideCountry.add(callOutSideCountryNO);
        callOutSideCountry.add(callOutSideCountryYES);
        callOutSideCountryYES.setActionCommand("YES");//case yes
        callOutSideCountryNO.setActionCommand("NO");//case No
        callOutSideCountryNO.setSelected(true);


        //-- South Buttons --//
        addToSchedule = new JButton("הוסף הפעלה");
        editToSchedule = new JButton("עדכן הפעלה");
        numberRangeButton = new JButton("הוסף טווחים");
        failActivation = new JButton("הכשל הפעלה");
        activationToFile = new JButton("הוצא נתונים");
        FNR = new JButton("הפק FNR");
        templateActivaion = new JButton("טופס מוכן");



        //-- Date --//
        // new format to Date //
        dateLabelFormatter = new DateLabelFormatter();
        UtilDateModel model = new UtilDateModel();
        datePicker = new JDatePicker(model);
        datePickerEv="";
        try {
            dateLabelFormatter.valueToString((Date)datePicker.getModel().getValue());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        //-----------------------------------------------------//
        //--------------- End of Initialization ---------------//
        //-----------------------------------------------------//


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
        JFrame parent1 = new JFrame();
        loginUI = new LoginUI(parent1);
        loginUI.setUserLoggedListener(new UserLoggedListener() {
            @Override
            public void setUserFirstNameLogged(String User) {
            }
        });
        addToSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFormToDataBase();
            }

        });
        editToSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    activationSipController.connect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    activationSipController.loadActivationSipToList();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String expertName = activationSipController.getExpertName(ActivationsMoves.FormId.getActivationId());
                if (ActivationsMoves.SessionId.getExpertName() == null)
                    ActivationsMoves.SessionId.setExpertName(expertName);
                uploadFormToDataBase();
                activationSipController.disconnect();
            }
        });
        numberRangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberRangesView = new NumberRangesView(mainPanel,activationId);
                numberRangesView.setVisible(true);
            }
        });
        activationToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wan = ChainIpAddress(wanAddressA.getText(),wanAddressB.getText(),wanAddressC.getText(),wanAddressD.getText());
                String lan = ChainIpAddress(lanAddressA.getText(),lanAddressB.getText(),lanAddressC.getText(),lanAddressD.getText());
                String ipExternal = ChainIpAddress(ipAddressA.getText(),ipAddressB.getText(),ipAddressC.getText(),ipAddressD.getText());
                //-- get Radio from callOutSideCountry --//
                String callOutSideCountryEv;
                String getcallOutSideCountryChoice = callOutSideCountry.getSelection().getActionCommand();
                if (getcallOutSideCountryChoice.equals("YES"))
                    callOutSideCountryEv = "כן";
                else
                    callOutSideCountryEv = "לא";
                ExpertToFileController controller = new ExpertToFileController(trunkNumber.getText(),(Integer)sbcPort.getValue(),Integer.parseInt(totalCalls.getText()),snbNumber.getText(),
                        wan,lan,ipExternal,(String)signalAddress.getSelectedItem(),(String)mediaAddress.getSelectedItem(),(String)areaCode.getSelectedItem(),emergencyCity.getText(),crNumber.getText(),callOutSideCountryEv);
                controller.exportToFile();
                JOptionPane.showMessageDialog(ActivationFormSIP.this,"הקובץ נוצר בהצלחה","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        templateActivaion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CODEC.setSelectedIndex(5);
                typeOfCalls.setSelectedIndex(3);
                identificationType.setSelectedIndex(1);
                signalAddress.setSelectedIndex(4);
                mediaAddress.setSelectedIndex(4);
            }
        });
        signalAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaAddress.setSelectedIndex(signalAddress.getSelectedIndex());
            }
        });
        FNR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumberRangeController numberRangeController = new NumberRangeController();
                if (ActivationsMoves.SessionId.getFromRange() == null || ActivationsMoves.SessionId.getToRange()== null) {
                    try {
                        numberRangeController.connect();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    try {
                        numberRangeController.loadNumberRangeFromDataBaseToList(activationId);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    numberRangeController.FNRtoFileSip(numberRangeController.getNumberRanges().get(0).getFromRange(), numberRangeController.getNumberRanges().get(0).getToRange());
                }
                else{
                    numberRangeController.FNRtoFileSip(ActivationsMoves.SessionId.getFromRange(), ActivationsMoves.SessionId.getToRange());

                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (ActivationsMoves.SessionId.getFromRange() != null && ActivationsMoves.SessionId.getToRange() != null)
                    ActivationsMoves.SessionId.remove();
                clearForm();
            }
        });
        disablePortAndTrunk();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("טופס התקנה"));
        add(mainPanel,BorderLayout.CENTER);
        FormControl();
        setModal(true);
        setSize(750, 700); // Size the Frame
        setLocationRelativeTo(parent);
    }
    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }
    private String ChainIpAddress(String a , String b , String c , String d){
        return a + "." + b + "." + c + "." + d ;
    }
    private boolean CheckIP(){

        boolean allGood;
        boolean finalResult = true;
        Integer wanA = Integer.parseInt(wanAddressA.getText());
        Integer wanB = Integer.parseInt(wanAddressB.getText());
        Integer wanC = Integer.parseInt(wanAddressC.getText());
        Integer wanD = Integer.parseInt(wanAddressD.getText());
        allGood = activationSipController.checkIP(wanA,wanB,wanC,wanD);
        if (!allGood) {
            wanAddressLabel.setForeground(Color.red);
            finalResult= false;
        }

        Integer lanA = Integer.parseInt(lanAddressA.getText());
        Integer lanB = Integer.parseInt(lanAddressB.getText());
        Integer lanC = Integer.parseInt(lanAddressC.getText());
        Integer lanD = Integer.parseInt(lanAddressD.getText());

        allGood = activationSipController.checkIP(lanA,lanB,lanC,lanD);
        if (!allGood) {
            lanAddressLabel.setForeground(Color.red);
            finalResult= false;
        }

        Integer ipA = Integer.parseInt(ipAddressA.getText());
        Integer ipB = Integer.parseInt(ipAddressB.getText());
        Integer ipC = Integer.parseInt(ipAddressC.getText());
        Integer ipD = Integer.parseInt(ipAddressD.getText());

        allGood = activationSipController.checkIP(ipA,ipB,ipC,ipD);
        if (!allGood) {
            ipAddressLabel.setForeground(Color.red);
            finalResult= false;
        }
        return finalResult;
    }

    private boolean CheckEmail(){
        return activationSipController.checkEmail(customerEmail.getText());
    }

    private boolean CheckInputDigits(){
        boolean flag=true;

        if (!activationSipController.checkInputDigits(totalNumbers.getText())) {
            totalNumbersLabel.setForeground(Color.red);
            flag = false;
        }
        else
            totalNumbersLabel.setForeground(Color.black);

        if (!activationSipController.checkInputDigits(totalCalls.getText())){
            totalCallsLabel.setForeground(Color.red);
            flag = false;
        }
        else
            totalCallsLabel.setForeground(Color.black);

        if (!activationSipController.checkInputDigits(wanAddressA.getText()) || !activationSipController.checkInputDigits(wanAddressB.getText()) ||
                !activationSipController.checkInputDigits(wanAddressC.getText()) || !activationSipController.checkInputDigits(wanAddressD.getText())) {
            wanAddressLabel.setForeground(Color.red);
            flag = false;
        }
        else
            wanAddressLabel.setForeground(Color.black);

        if (!activationSipController.checkInputDigits(lanAddressA.getText()) || !activationSipController.checkInputDigits(lanAddressB.getText()) ||
                !activationSipController.checkInputDigits(lanAddressC.getText()) || !activationSipController.checkInputDigits(lanAddressD.getText())) {
            lanAddressLabel.setForeground(Color.red);
            flag = false;
        }
        else
            lanAddressLabel.setForeground(Color.black);

        if (!activationSipController.checkInputDigits(ipAddressA.getText()) || !activationSipController.checkInputDigits(ipAddressB.getText()) ||
                !activationSipController.checkInputDigits(ipAddressC.getText()) || !activationSipController.checkInputDigits(ipAddressD.getText())) {
            ipAddressLabel.setForeground(Color.red);
            flag = false;
        }
        else
            ipAddressLabel.setForeground(Color.black);

        return flag;
    }
    //check that cells arent empty
    private boolean CheckEmptyCells(){
        boolean allGood = true;

        if (datePickerEv.isEmpty()) {
            dateLabel.setForeground(Color.red);
            allGood = false;
        }
        else
            dateLabel.setForeground(Color.black);

        if (activationSipController.checkEmptyCells(customerID.getText())){
            customerIDLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            customerIDLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(customerName.getText())) {
            customerNameLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            customerNameLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(contactName.getText())) {
            contactNameLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            contactNameLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(customerPhoneNumber.getText())) {
            customerPhoneNumberLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            customerPhoneNumberLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(customerEmail.getText())) {
            customerEmailLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            customerEmailLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(customerTechName.getText())) {
            customerTechNameLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            customerTechNameLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(customerTechPhoneNumber.getText())) {
            customerTechPhoneNumberLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            customerTechPhoneNumberLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(pbxType.getText())) {
            pbxTypeLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            pbxTypeLabel.setForeground(Color.black);
        }

        if (connectionType.getSelectedIndex() == 0) {
            connectionTypeLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            connectionTypeLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(infrastructure.getText())) {
            infrastructureLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            infrastructureLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(totalNumbers.getText())) {
            totalNumbersLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            totalNumbersLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(totalCalls.getText())) {
            totalCallsLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            totalCallsLabel.setForeground(Color.black);
        }

        if (routerTypeTextField.isEnabled()) {
            if (activationSipController.checkEmptyCells(routerTypeTextField.getText())) {
                routerTypeLabel.setForeground(Color.red);
                allGood = false;
            } else {
                routerTypeLabel.setForeground(Color.black);
            }
        }
        else
            routerTypeLabel.setForeground(Color.black);

        if (CODEC.getSelectedIndex() == 0) {
            CODECLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            CODECLabel.setForeground(Color.black);
        }

        if (typeOfCalls.getSelectedIndex() == 0) {
            typeOfCallsLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            typeOfCallsLabel.setForeground(Color.black);
        }

        if (identificationType.getSelectedIndex() == 0) {
            identificationTypeLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            identificationTypeLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(internetUser.getText())) {
            internetUserLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            internetUserLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(snbNumber.getText())) {
            snbNumberLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            snbNumberLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(totalCalls.getText())) {
            totalCallsLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            totalCallsLabel.setForeground(Color.black);
        }

        if (signalAddress.getSelectedIndex() == 0) {
            signalAddressLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            signalAddressLabel.setForeground(Color.black);
        }

        if (mediaAddress.getSelectedIndex() == 0) {
            mediaAddressLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            mediaAddressLabel.setForeground(Color.black);
        }

        if (areaCode.getSelectedIndex() == 0) {
            areaCodeLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            areaCodeLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(emergencyCity.getText())) {
            emergencyCityLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            emergencyCityLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(crNumber.getText())) {
            crNumberLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            crNumberLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(crNumber.getText())) {
            crNumberLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            crNumberLabel.setForeground(Color.black);
        }
        if (!usersManagerController.getUserFirstNameLogged().getUsersType().toString().equals("ProjectManager")) {
            if (trunkNumber.getText().isEmpty() || trunkNumber.getText().trim().isEmpty()) {
                trunkNumberLabel.setForeground(Color.red);
                allGood = false;
            } else {
                trunkNumberLabel.setForeground(Color.black);
            }
        }

        if (activationSipController.checkEmptyCells(wanAddressA.getText()) || activationSipController.checkEmptyCells(wanAddressB.getText())
           || activationSipController.checkEmptyCells(wanAddressC.getText()) || activationSipController.checkEmptyCells(wanAddressD.getText())) {
            wanAddressLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            wanAddressLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(lanAddressA.getText()) || activationSipController.checkEmptyCells(lanAddressB.getText())
                || activationSipController.checkEmptyCells(lanAddressC.getText()) || activationSipController.checkEmptyCells(lanAddressD.getText())) {
            lanAddressLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            lanAddressLabel.setForeground(Color.black);
        }

        if (activationSipController.checkEmptyCells(ipAddressA.getText()) || activationSipController.checkEmptyCells(ipAddressB.getText())
                || activationSipController.checkEmptyCells(ipAddressC.getText()) || activationSipController.checkEmptyCells(ipAddressD.getText())) {
            ipAddressLabel.setForeground(Color.red);
            allGood = false;
        }
        else {
            ipAddressLabel.setForeground(Color.black);
        }

        return allGood;
    }
    //-- Layout control panels --//
    private void FormControl () {

        //-- Form Panel --//
        formPanelLeft.setLayout(new GridBagLayout());
        GridBagConstraints gcLeft = new GridBagConstraints();
        gcLeft.fill = GridBagConstraints.NONE;

        formPanelRight.setLayout(new GridBagLayout());
        GridBagConstraints gcRight = new GridBagConstraints();
        gcRight.fill = GridBagConstraints.NONE;

        int rightLeftRow = 100;

        //----------------------------- Left Rows -----------------------------//
        gcLeft.weighty=1;
        gcLeft.weightx=1;
        gcLeft.gridy = 0;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(identificationType,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(identificationTypeLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(snbNumber,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(snbNumberLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(numberRangeButton,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(numberRangeLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(wanAddressD,gcLeft);
        gcLeft.insets = new Insets(5,0,0,40);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,45);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(wanAddressC,gcLeft);
        gcLeft.insets = new Insets(5,0,0,85);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,90);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(wanAddressB,gcLeft);
        gcLeft.insets = new Insets(5,0,0,130);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,135);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(wanAddressA,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(wanAddressLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(lanAddressD,gcLeft);
        gcLeft.insets = new Insets(5,0,0,40);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,45);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(lanAddressC,gcLeft);
        gcLeft.insets = new Insets(5,0,0,85);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,90);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(lanAddressB,gcLeft);
        gcLeft.insets = new Insets(5,0,0,130);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,135);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(lanAddressA,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(lanAddressLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(ipAddressD,gcLeft);
        gcLeft.insets = new Insets(5,0,0,40);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,45);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(ipAddressC,gcLeft);
        gcLeft.insets = new Insets(5,0,0,85);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,90);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(ipAddressB,gcLeft);
        gcLeft.insets = new Insets(5,0,0,130);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(new JLabel("."),gcLeft);
        gcLeft.insets = new Insets(0,0,0,135);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(ipAddressA,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(ipAddressLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(internetUser,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(internetUserLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(signalAddress,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(signalAddressLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(mediaAddress,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(mediaAddressLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(areaCode,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(areaCodeLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(emergencyCity,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(emergencyCityLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,10);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(callOutSideCountryYES,gcLeft);
        gcLeft.insets = new Insets(0,rightLeftRow,0,50);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(callOutSideCountryNO,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(callOutSideCountryLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(datePicker,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(dateLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(crNumber,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(crNumberLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(trunkNumber,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(trunkNumberLabel,gcLeft);

        gcLeft.gridy ++;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0,rightLeftRow,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(sbcPort,gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0,0,0,0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        formPanelLeft.add(sbcPortLabel,gcLeft);


        //----------------------------- Right Rows -----------------------------//
        gcRight.weighty=1;
        gcRight.weightx=1;
        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerID,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,75);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(customerIDLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(customerNameLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(contactName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(contactNameLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerPhoneNumber,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(customerPhoneNumberLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerEmail,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(customerEmailLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerTechName,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(customerTechNameLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(customerTechPhoneNumber,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(customerTechPhoneNumberLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(pbxType,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(pbxTypeLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(connectionType,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(connectionTypeLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(infrastructure,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(infrastructureLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(totalNumbers,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(totalNumbersLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(totalCalls,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(totalCallsLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(routerTypeNO,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(fireWallLabel,gcRight);
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
        formPanelRight.add(routerTypeLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(CODEC,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(CODECLabel,gcRight);

        gcRight.gridy++;
        gcRight.gridx = 0;
        gcRight.insets = new Insets(0,100,0,0);
        gcRight.anchor = GridBagConstraints.LINE_END;
        formPanelRight.add(typeOfCalls,gcRight);
        gcRight.gridx++;
        gcRight.insets = new Insets(0,0,0,0);
        gcRight.anchor = GridBagConstraints.LINE_START;
        formPanelRight.add(typeOfCallsLabel,gcRight);


        //-- Buttons Panel --//
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(FNR);
        buttonsPanel.add(templateActivaion);
        buttonsPanel.add(activationToFile);
        buttonsPanel.add(failActivation);
        setAddOrEditBotton(indexOfButton);


        //-- Title Panel Top --//
        formPanelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        formPanelTop.setBorder(BorderFactory.createLineBorder(Color.black));
        wellcome.setFont(new Font("Arial",Font.PLAIN,36));
        formPanelTop.add(wellcome);


        // Add sub panels //
        setLayout(new BorderLayout());
        add(formPanelTop,BorderLayout.NORTH);
        add(formPanelLeft,BorderLayout.WEST);
        add(formPanelRight,BorderLayout.EAST);
        add(buttonsPanel,BorderLayout.SOUTH);

    }
    public void setAddOrEditBotton(int i){
        if (i==0)
            buttonsPanel.add(addToSchedule);
        else
            buttonsPanel.add(editToSchedule);
    }
    public void uploadFormToDataBase(){
        String customerIDEv;
        String customerNameEv;
        String contactNameEv;
        String customerPhoneNumberEv;
        String customerEmailEv;
        String customerTechNameEv;
        String customerTechPhoneNumberEv;
        String pbxTypeEv;
        String typeOfCallsEv;
        String identificationTypeEv;
        int totalNumbersEv;
        String snbNumberEv;
        String areaCodeEv;
        String emergencyCityEv;
        String callOutSideCountryEv;
        String crNumberEv;
        String trunkNumberEv;
        String wanAddressEv;
        String lanAddressEv;
        String ipAddressEv;
        String internetUserEv;
        String infrastructureEv;
        String routerTypeEv;
        String CODECEv;
        int totalCallsEv;
        String signalAddressEv;
        String mediaAddressEv;
        int sbcPortEv;
        String expertName;
        String connectionTypeEv;
        String projectManagerEv;
        int idSession = ActivationsMoves.FormId.getActivationId();

        // -- Date Handle --//

        try {
            datePickerEv = dateLabelFormatter.valueToString((Date)datePicker.getModel().getValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String lastUpdateEv = formatter.format(date);


        if (CheckEmptyCells()) {
            if (CheckInputDigits() && CheckIP() && CheckEmail() && CheckEmptyCells() && CheckPhoneNumber() && CheckSnb())
            {
                customerIDEv = customerID.getText();
                totalNumbersEv = Integer.parseInt(totalNumbers.getText());
                sbcPortEv = Integer.parseInt(sbcPort.getValue().toString());
                totalCallsEv = Integer.parseInt(totalCalls.getText());
                customerNameEv = customerName.getText();
                contactNameEv = contactName.getText();
                customerPhoneNumberEv = customerPhoneNumber.getText();
                customerEmailEv = customerEmail.getText();
                customerTechNameEv = customerTechName.getText();
                customerTechPhoneNumberEv = customerTechPhoneNumber.getText();
                typeOfCallsEv = (String)typeOfCalls.getSelectedItem();
                pbxTypeEv = pbxType.getText();

                identificationTypeEv = (String)identificationType.getSelectedItem();
                connectionTypeEv = (String)connectionType.getSelectedItem();

                snbNumberEv = snbNumber.getText();
                areaCodeEv = (String)areaCode.getSelectedItem();
                emergencyCityEv = emergencyCity.getText();
                int numofFailsEv = 1;

                //-- get Radio from callOutSideCountry --//
                String getcallOutSideCountryChoice = callOutSideCountry.getSelection().getActionCommand();
                if (getcallOutSideCountryChoice.equals("YES"))
                    callOutSideCountryEv = "כן";
                else
                    callOutSideCountryEv = "לא";

                crNumberEv = crNumber.getText();
                trunkNumberEv = trunkNumber.getText();
                wanAddressEv = wanAddressA.getText() + "." + wanAddressB.getText() + "." + wanAddressC.getText() + "." + wanAddressD.getText();
                lanAddressEv = lanAddressA.getText() + "." + lanAddressB.getText() + "." + lanAddressC.getText() + "." + lanAddressD.getText();
                ipAddressEv =  ipAddressA.getText() + "." + ipAddressB.getText() + "." + ipAddressC.getText() + "." + ipAddressD.getText();
                internetUserEv = internetUser.getText();
                infrastructureEv = infrastructure.getText();
                routerTypeEv = routerTypeTextField.getText();
                CODECEv = (String)CODEC.getSelectedItem();
                signalAddressEv = (String)signalAddress.getSelectedItem();
                mediaAddressEv = (String)mediaAddress.getSelectedItem();
                expertName = ActivationsMoves.SessionId.getExpertName();
                projectManagerEv = ActivationsMoves.SessionId.getUserName();
                String status = "לא";

                FormEvent evForm;
                FormEvent evNumbers;
                if (indexOfButton ==0){

                    //--Create New --//
                    expertName="";
                    evForm= new FormEvent(this,customerIDEv,customerNameEv,contactNameEv,customerPhoneNumberEv,customerEmailEv,customerTechNameEv,customerTechPhoneNumberEv,pbxTypeEv,typeOfCallsEv,identificationTypeEv,
                        totalNumbersEv,snbNumberEv,areaCodeEv,emergencyCityEv,callOutSideCountryEv,crNumberEv,trunkNumberEv,datePickerEv,wanAddressEv,lanAddressEv,ipAddressEv,internetUserEv,
                        infrastructureEv,routerTypeEv,CODECEv,totalCallsEv,signalAddressEv,mediaAddressEv,sbcPortEv,expertName,connectionTypeEv,projectManagerEv,numofFailsEv,status,lastUpdateEv);

                    evNumbers = new FormEvent(this,ActivationsMoves.SessionId.getFromRange(),ActivationsMoves.SessionId.getToRange(),trunkNumberEv );
                }
                else {

                    //--Edited --//
                    evForm = new FormEvent(this,idSession,customerIDEv,customerNameEv,contactNameEv,customerPhoneNumberEv,customerEmailEv,customerTechNameEv,customerTechPhoneNumberEv,pbxTypeEv,typeOfCallsEv,identificationTypeEv,
                            totalNumbersEv,snbNumberEv,areaCodeEv,emergencyCityEv,callOutSideCountryEv,crNumberEv,trunkNumberEv,datePickerEv,wanAddressEv,lanAddressEv,ipAddressEv,internetUserEv,
                            infrastructureEv,routerTypeEv,CODECEv,totalCallsEv,signalAddressEv,mediaAddressEv,sbcPortEv,expertName,connectionTypeEv,projectManagerEv,status,lastUpdateEv);

                    evNumbers = new FormEvent(this,ActivationsMoves.SessionId.getFromRange(),ActivationsMoves.SessionId.getToRange(),trunkNumberEv);
                }
                formListener.formEventOccurred(evForm);
                formListener.formEventOccurredNumber(evNumbers);

                if (ActivationsMoves.SessionId.getFromRange() != null && ActivationsMoves.SessionId.getToRange() != null)
                    ActivationsMoves.SessionId.remove();
                clearForm();
                dispose();
            }
            else {
                massages();
            }
        }
        else
            JOptionPane.showMessageDialog(ActivationFormSIP.this,"נא השלם את הנתונים באדום","Error",JOptionPane.ERROR_MESSAGE);
    }

    private void clearForm(){
        customerID.setText("");
        customerIDLabel.setForeground(Color.black);
        totalNumbers.setText("");
        totalNumbersLabel.setForeground(Color.black);
        sbcPort.setValue(7500);
        sbcPortLabel.setForeground(Color.black);
        totalCalls.setText("");
        totalCallsLabel.setForeground(Color.black);
        customerName.setText("");
        customerNameLabel.setForeground(Color.black);
        contactName.setText("");
        contactNameLabel.setForeground(Color.black);
        customerPhoneNumber.setText("");
        customerPhoneNumberLabel.setForeground(Color.black);
        customerEmail.setText("");
        customerEmailLabel.setForeground(Color.black);
        customerTechName.setText("");
        customerTechNameLabel.setForeground(Color.black);
        customerTechPhoneNumber.setText("");
        customerTechPhoneNumberLabel.setForeground(Color.black);
        connectionType.setSelectedIndex(0);
        connectionTypeLabel.setForeground(Color.black);
        typeOfCalls.setSelectedIndex(0);
        typeOfCallsLabel.setForeground(Color.black);
        pbxType.setText("");
        pbxTypeLabel.setForeground(Color.black);
        typeOfCalls.setSelectedIndex(0);
        typeOfCallsLabel.setForeground(Color.black);
        identificationType.setSelectedIndex(0);
        identificationTypeLabel.setForeground(Color.black);
        snbNumber.setText("");
        snbNumberLabel.setForeground(Color.black);
        areaCode.setSelectedIndex(0);
        areaCodeLabel.setForeground(Color.black);
        emergencyCity.setText("");
        emergencyCityLabel.setForeground(Color.black);
        routerTypeGroup.setSelected(routerTypeNO.getModel(),true);
        routerTypeLabel.setForeground(Color.black);
        routerTypeTextField.setText("");
        callOutSideCountry.setSelected(callOutSideCountryNO.getModel(),true);
        callOutSideCountryLabel.setForeground(Color.black);
        crNumber.setText("");
        crNumberLabel.setForeground(Color.black);
        trunkNumber.setText("");
        trunkNumberLabel.setForeground(Color.black);
        wanAddressA.setText("");
        wanAddressB.setText("");
        wanAddressC.setText("");
        wanAddressD.setText("");
        wanAddressLabel.setForeground(Color.black);
        lanAddressA.setText("");
        lanAddressB.setText("");
        lanAddressC.setText("");
        lanAddressD.setText("");
        lanAddressLabel.setForeground(Color.black);
        ipAddressA.setText("");
        ipAddressB.setText("");
        ipAddressC.setText("");
        ipAddressD.setText("");
        ipAddressLabel.setForeground(Color.black);
        internetUser.setText("");
        internetUserLabel.setForeground(Color.black);
        infrastructure.setText("");
        infrastructureLabel.setForeground(Color.black);
        routerTypeTextField.setText("");
        routerTypeLabel.setForeground(Color.black);
        CODEC.setSelectedIndex(0);
        CODECLabel.setForeground(Color.black);
        signalAddress.setSelectedIndex(0);
        signalAddressLabel.setForeground(Color.black);
        mediaAddress.setSelectedIndex(0);
        mediaAddressLabel.setForeground(Color.black);
        datePicker.getModel().setSelected(false);
        dateLabel.setForeground(Color.black);
    }

    private void massages(){
        if (CheckEmptyCells()) {
            if (!CheckIP())
                JOptionPane.showMessageDialog(ActivationFormSIP.this, "כתובת IP אינה תקינה", "Error", JOptionPane.ERROR_MESSAGE);
            if (!CheckInputDigits())
                JOptionPane.showMessageDialog(ActivationFormSIP.this, "יש להזין ערך מספרי בלבד", "Error", JOptionPane.ERROR_MESSAGE);
            if (!CheckEmail())
                JOptionPane.showMessageDialog(ActivationFormSIP.this, "כתובת email אינה תקינה", "Error", JOptionPane.ERROR_MESSAGE);
            if (!CheckPhoneNumber())
                JOptionPane.showMessageDialog(ActivationFormSIP.this, "מספר טלפון אינו תקין", "Error", JOptionPane.ERROR_MESSAGE);
            if (!CheckSnb())
                JOptionPane.showMessageDialog(ActivationFormSIP.this, "מספר מוביל אינו תקין", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean CheckPhoneNumber(){
        if (activationSipController.checkPhoneNumber(customerPhoneNumber.getText()) && activationSipController.checkPhoneNumber(customerTechPhoneNumber.getText()))
            return true;
        return false;
    }

    private boolean CheckSnb(){
        return activationSipController.checkSnb(snbNumber.getText());
    }

    public void disablePortAndTrunk(){
        String user = usersManagerController.getUserFirstNameLogged().getUsersType().toString();
        if (user.equals("ProjectManager")){
            ((JSpinner.DefaultEditor) sbcPort.getEditor()).getTextField().setEditable(false);
            sbcPort.setEnabled(false);
            trunkNumber.setEnabled(false);
        }
    }
}
