package View;

import Controller.*;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ListOfActivationView extends JDialog {

    private JList activations;
    private JPanel panel;
    private JDatePicker fromDate;
    private JDatePicker toDate;
    private JPanel formPanelUp;
    private JPanel formPanelDownLeft;
    private JPanel formPanelDownRight;
    private JButton view;
    private String DateChoose;
    private ActivationFormSIP activationFormSIP;
    private String datePickerEvFrom;
    private String datePickerEvTo;
    private JLabel expertName;
    private JLabel projectManagerName;
    private GetDataFromSipListener getDataFromSipListener;
    private DefaultListModel actModel;
    private List<Integer> activationsIdArrayList;
    private ActivationSipController activationSipController;
    private NumberRangeController numberRangeController;
    private DateLabelFormatter dateLabelFormatterFrom;
    private DateLabelFormatter dateLabelFormatterTo;

    private int currentId;

    public ListOfActivationView(JFrame parent){
        activations = new JList();
        panel = new JPanel();
        formPanelUp = new JPanel();
        formPanelDownLeft = new JPanel();
        formPanelDownRight = new JPanel();
        view = new JButton("הצג");
        expertName = new JLabel();
        projectManagerName = new JLabel();
        activationsIdArrayList = new LinkedList<>();
        activationSipController = new ActivationSipController();
        numberRangeController = new NumberRangeController();

        // New format to Date From//
        UtilDateModel modelFrom = new UtilDateModel();
        fromDate = new JDatePicker(modelFrom);
        datePickerEvFrom="";
        dateLabelFormatterFrom = new DateLabelFormatter();

        // New format to Date to//
        UtilDateModel modelTo = new UtilDateModel();
        toDate = new JDatePicker(modelTo);
        datePickerEvTo="";
        dateLabelFormatterTo = new DateLabelFormatter();

        //-- View Get List from Dates --//
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadActivationToJlist();
            }
        });
        activations.setBorder(BorderFactory.createEtchedBorder()); // create just frame border
        activations.setSelectedIndex(1);//set default index selected as 1 allways

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(activations);
        activations.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setPreferredSize(new Dimension(250,200));

        activations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (activations.getModel().getSize() != 0) {
                    int selectedActivation,index;
                    for (index = 0; index < activationSipController.getSipActivation().size(); index++) {
                        if (activationsIdArrayList.get(activations.getSelectedIndex()) == activationSipController.getSipActivation().get(index).getId()) {
                            break;
                        }
                    }
                    selectedActivation = index;
                    if (e.getButton() == MouseEvent.BUTTON1) //Click Once
                    {
                        loadActivationToJlist();
                        expertName.setText(activationSipController.getSipActivation().get(selectedActivation).getExpertFirstName());
                        expertName.setForeground(Color.BLUE);
                        projectManagerName.setText(activationSipController.getSipActivation().get(selectedActivation).getProjectManagerFirstName());
                        projectManagerName.setForeground(Color.BLUE);
                        //numberOfSuccess
                    }
                    if (e.getClickCount() == 2) //Double click
                    {
                        loadActivationToJlist();
                        currentId = activationSipController.getSipActivation().get(selectedActivation).getId();
                        ActivationsMoves.FormId.setActivationId(currentId);
                        activationFormSIP = new ActivationFormSIP(panel,1,currentId);
                        activationFormSIP.customerID.setText(activationSipController.getSipActivation().get(selectedActivation).getCustomerID());
                        activationFormSIP.customerName.setText(activationSipController.getSipActivation().get(selectedActivation).getCustomerName());
                        activationFormSIP.contactName.setText(activationSipController.getSipActivation().get(selectedActivation).getContactName());
                        activationFormSIP.customerPhoneNumber.setText(activationSipController.getSipActivation().get(selectedActivation).getCustomerPhoneNumber());
                        activationFormSIP.customerEmail.setText(activationSipController.getSipActivation().get(selectedActivation).getCustomerEmail());
                        activationFormSIP.customerTechName.setText(activationSipController.getSipActivation().get(selectedActivation).getCustomerTechName());
                        activationFormSIP.customerTechPhoneNumber.setText(activationSipController.getSipActivation().get(selectedActivation).getCustomerTechPhoneNumber());
                        activationFormSIP.pbxType.setText(activationSipController.getSipActivation().get(selectedActivation).getPbxType());
                        activationFormSIP.infrastructure.setText(activationSipController.getSipActivation().get(selectedActivation).getInfrastructure());
                        for (int i = 0; i < activationFormSIP.connectionType.getItemCount(); i++) {
                            String connectionType = (String) activationFormSIP.connectionType.getModel().getElementAt(i);
                            String actConnectionType = activationSipController.getSipActivation().get(selectedActivation).getConnectionType();
                            if (actConnectionType.equals(connectionType))
                                activationFormSIP.connectionType.setSelectedIndex(i);
                        }
                        activationFormSIP.totalNumbers.setText(Integer.toString(activationSipController.getSipActivation().get(selectedActivation).getTotalNumbers()));
                        activationFormSIP.totalCalls.setText(Integer.toString(activationSipController.getSipActivation().get(selectedActivation).getTotalCalls()));

                        String routerType = activationSipController.getSipActivation().get(selectedActivation).getRouterType();
                        if (routerType.isEmpty())
                            activationFormSIP.routerTypeGroup.setSelected(activationFormSIP.routerTypeNO.getModel(), true);
                        else {
                            activationFormSIP.routerTypeGroup.setSelected(activationFormSIP.routerTypeYES.getModel(), true);
                            activationFormSIP.routerTypeTextField.setText(activationSipController.getSipActivation().get(selectedActivation).getRouterType());
                            activationFormSIP.routerTypeTextField.setEnabled(true);
                        }

                        activationFormSIP.routerTypeTextField.getText();

                        for (int i = 0; i < activationFormSIP.CODEC.getItemCount(); i++) {
                            String CODEC = (String) activationFormSIP.CODEC.getModel().getElementAt(i);
                            String actCODEC = activationSipController.getSipActivation().get(selectedActivation).getCODEC();
                            if (CODEC.equals(actCODEC))
                                activationFormSIP.CODEC.setSelectedIndex(i);
                        }

                        for (int i = 0; i < activationFormSIP.typeOfCalls.getItemCount(); i++) {
                            String typeOfCalls = (String) activationFormSIP.typeOfCalls.getModel().getElementAt(i);
                            String actTypeOfCalls = activationSipController.getSipActivation().get(selectedActivation).getTypeOfCalls();
                            if (typeOfCalls.equals(actTypeOfCalls))
                                activationFormSIP.typeOfCalls.setSelectedIndex(i);
                        }

                        for (int i = 0; i < activationFormSIP.identificationType.getItemCount(); i++) {
                            String identificationType = (String) activationFormSIP.identificationType.getModel().getElementAt(i);
                            String actIdentificationType = activationSipController.getSipActivation().get(selectedActivation).getIdentificationType();
                            if (identificationType.equals(actIdentificationType))
                                activationFormSIP.identificationType.setSelectedIndex(i);
                        }
                        activationFormSIP.snbNumber.setText(activationSipController.getSipActivation().get(selectedActivation).getSnbNumber());

                        //--WAN--//
                        String wan = "";
                        for (int j = 0, i = 0; i < activationSipController.getSipActivation().get(selectedActivation).getWanAddress().length(); i++) {
                            char a = activationSipController.getSipActivation().get(selectedActivation).getWanAddress().charAt(i);
                            if (a != '.')
                                wan += a;
                            if (a == '.') {
                                j++;
                                if (j == 1) {
                                    activationFormSIP.wanAddressA.setText(wan);
                                    wan = "";
                                }
                                if (j == 2) {
                                    activationFormSIP.wanAddressB.setText(wan);
                                    wan = "";
                                }
                                if (j == 3) {
                                    activationFormSIP.wanAddressC.setText(wan);
                                    wan = "";
                                }
                            }
                            activationFormSIP.wanAddressD.setText(wan);
                        }

                        //--LAN--//
                        String lan = "";
                        for (int j = 0, i = 0; i < activationSipController.getSipActivation().get(selectedActivation).getLanAddress().length(); i++) {
                            char a = activationSipController.getSipActivation().get(selectedActivation).getLanAddress().charAt(i);

                            if (a != '.')
                                lan += a;
                            if (a == '.') {
                                j++;
                                if (j == 1) {
                                    activationFormSIP.lanAddressA.setText(lan);
                                    lan = "";
                                }
                                if (j == 2) {
                                    activationFormSIP.lanAddressB.setText(lan);
                                    lan = "";
                                }
                                if (j == 3) {
                                    activationFormSIP.lanAddressC.setText(lan);
                                    lan = "";
                                }
                            }
                            activationFormSIP.lanAddressD.setText(lan);
                        }

                        //--IP--//
                        String ip = "";
                        for (int j = 0, i = 0; i < activationSipController.getSipActivation().get(selectedActivation).getIpAddress().length(); i++) {
                            char a = activationSipController.getSipActivation().get(selectedActivation).getIpAddress().charAt(i);

                            if (a != '.')
                                ip += a;
                            if (a == '.') {
                                j++;
                                if (j == 1) {
                                    activationFormSIP.ipAddressA.setText(ip);
                                    ip = "";
                                }
                                if (j == 2) {
                                    activationFormSIP.ipAddressB.setText(ip);
                                    ip = "";
                                }
                                if (j == 3) {
                                    activationFormSIP.ipAddressC.setText(ip);
                                    ip = "";
                                }
                            }
                            activationFormSIP.ipAddressD.setText(ip);
                        }
                        activationFormSIP.internetUser.setText(activationSipController.getSipActivation().get(selectedActivation).getInternetUser());

                        for (int i = 0; i < activationFormSIP.signalAddress.getItemCount(); i++) {
                            String signalAddress = (String) activationFormSIP.signalAddress.getModel().getElementAt(i);
                            String actSignalAddress = activationSipController.getSipActivation().get(selectedActivation).getSignalAddress();
                            if (signalAddress.equals(actSignalAddress))
                                activationFormSIP.signalAddress.setSelectedIndex(i);
                        }

                        for (int i = 0; i < activationFormSIP.mediaAddress.getItemCount(); i++) {
                            String mediaAddress = (String) activationFormSIP.mediaAddress.getModel().getElementAt(i);
                            String actMediaAddress = activationSipController.getSipActivation().get(selectedActivation).getMediaAddress();
                            if (mediaAddress.equals(actMediaAddress))
                                activationFormSIP.mediaAddress.setSelectedIndex(i);
                        }

                        for (int i = 0; i < activationFormSIP.areaCode.getItemCount(); i++) {
                            String areaCode = (String) activationFormSIP.areaCode.getModel().getElementAt(i);
                            String actAreaCode = activationSipController.getSipActivation().get(selectedActivation).getAreaCode();
                            if (areaCode.equals(actAreaCode))
                                activationFormSIP.areaCode.setSelectedIndex(i);
                        }

                        activationFormSIP.emergencyCity.setText(activationSipController.getSipActivation().get(selectedActivation).getEmergencyCity());

                        //-- DATE --//
                        DatePickerEdit(selectedActivation);

                        String callOutSideCountry = activationSipController.getSipActivation().get(selectedActivation).getCallOutSideCountry();
                        if (callOutSideCountry.equals("לא"))
                            activationFormSIP.callOutSideCountry.setSelected(activationFormSIP.callOutSideCountryNO.getModel(), true);
                        else
                            activationFormSIP.callOutSideCountry.setSelected(activationFormSIP.callOutSideCountryYES.getModel(), true);

                        activationFormSIP.crNumber.setText(activationSipController.getSipActivation().get(selectedActivation).getCrNumber());
                        activationFormSIP.trunkNumber.setText(activationSipController.getSipActivation().get(selectedActivation).getTrunkNumber());
                        activationFormSIP.sbcPort.setValue(activationSipController.getSipActivation().get(selectedActivation).getSbcPort());

                        activationFormSIP.setFormListener(new FormListener() {
                            @Override
                            public void formEventOccurred(FormEvent ev) {
                                getDataFromSipListener.updateActivation(ev);
                            }

                            @Override
                            public void formEventOccurredNumber(FormEvent e) {
                                numberRangeController.clearNumberRange();
                                numberRangeController.addNumberRange(e);
                                try {
                                    numberRangeController.connect();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                                try {
                                    numberRangeController.updateNumberRangeToDataBase(ActivationsMoves.FormId.getActivationId());
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                numberRangeController.disconnect();
                            }

                            @Override
                            public void formUpdateFails() {

                            }
                        });
                        activationFormSIP.failActivation.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    activationSipController.connect();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                                try {
                                    activationSipController.failActivation(ActivationsMoves.FormId.getActivationId());
                                    activationSipController.loadActivationSipToList();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                String fails = "הבקשה בוצע מספר ההכשלות הוא : " + activationSipController.getSipActivation().get(selectedActivation).getNumOfFails();
                                JOptionPane.showMessageDialog(ListOfActivationView.this, fails , "Info", JOptionPane.INFORMATION_MESSAGE);
                                activationSipController.disconnect();
                                getDataFromSipListener.UpdateNumberRange();


                            }
                        });
                        activationFormSIP.templateActivaion.setEnabled(false);
                        activationFormSIP.setVisible(true);
                    }
                }
            }
        });

        //-- Form Panel --//
        formPanelUp.setLayout(new GridBagLayout());
        GridBagConstraints gcUp = new GridBagConstraints();
        gcUp.fill = GridBagConstraints.NONE;

        formPanelDownLeft.setLayout(new GridBagLayout());
        GridBagConstraints gcDownLeft = new GridBagConstraints();
        gcDownLeft.fill = GridBagConstraints.NONE;


        formPanelDownRight.setLayout(new GridBagLayout());
        GridBagConstraints gcDownRight = new GridBagConstraints();
        gcDownRight.fill = GridBagConstraints.NONE;

        int top = 50;

        //-- Up Rows --//
        gcUp.weighty=1;
        gcUp.weightx=2;

        gcUp.gridy = 0;
        gcUp.gridx = 0;

        gcUp.insets = new Insets(top,30,top,0);
        gcUp.anchor = GridBagConstraints.LINE_START;
        formPanelUp.add(view,gcUp);
        gcUp.gridx++;
        gcUp.insets = new Insets(top,0,top,0);
        gcUp.anchor = GridBagConstraints.LINE_END;
        formPanelUp.add(toDate,gcUp);
        gcUp.gridx++;
        gcUp.insets = new Insets(top,0,top,0);
        gcUp.anchor = GridBagConstraints.LINE_START;
        formPanelUp.add(new JLabel("תאריך סיום : "),gcUp);
        gcUp.gridx++;
        gcUp.insets = new Insets(top,0,top,0);
        gcUp.anchor = GridBagConstraints.LINE_END;
        formPanelUp.add(fromDate,gcUp);
        gcUp.gridx++;
        gcUp.insets = new Insets(top,0,top,30);
        gcUp.anchor = GridBagConstraints.LINE_START;
        formPanelUp.add(new JLabel("תאריך התחלה : "),gcUp);


        //-- Down Left Rows --//
        gcDownLeft.weighty=1;
        gcDownLeft.weightx=1;
        gcDownLeft.gridy = 0;
        gcDownLeft.gridx = 0;
        gcDownLeft.insets = new Insets(0,50,0,50);
        gcDownLeft.anchor = GridBagConstraints.CENTER;
        formPanelDownLeft.add(scrollPane,gcDownLeft);


        //-- Down Left Rows --//
        gcDownRight.weighty=1;
        gcDownRight.weightx=1;
        gcDownRight.gridx=0;
        gcDownRight.gridy=0;
        gcDownRight.insets = new Insets(100,200,0,10);
        gcDownRight.anchor = GridBagConstraints.FIRST_LINE_END;
        formPanelDownRight.add(expertName,gcDownRight);
        gcDownRight.gridx++;
        gcDownRight.insets = new Insets(100,0,0,10);
        gcDownRight.anchor = GridBagConstraints.FIRST_LINE_START;
        formPanelDownRight.add(new JLabel("שם המומחה : "),gcDownRight);


        gcDownRight.gridx=0;
        gcDownRight.gridy++;
        gcDownRight.insets = new Insets(0,200,100,10);
        gcDownRight.anchor = GridBagConstraints.FIRST_LINE_END;
        formPanelDownRight.add(projectManagerName,gcDownRight);
        gcDownRight.gridx++;
        gcDownRight.insets = new Insets(0,0,100,10);
        gcDownRight.anchor = GridBagConstraints.FIRST_LINE_START;
        formPanelDownRight.add(new JLabel("שם מנהל הפרוייקט : "),gcDownRight);

        // Add sub panels //
        setLayout(new BorderLayout());
        formPanelUp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray),"בחר תאריך"));
        formPanelDownLeft.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray),"תוצאות"));
        //formPanelDownRight.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray),"נתונים"));
        add(formPanelUp,BorderLayout.NORTH);
        add(formPanelDownLeft,BorderLayout.WEST);
        add(formPanelDownRight,BorderLayout.EAST);


        setModal(true);
        setSize(700, 500); // Size the Frame
        setLocationRelativeTo(parent);

    }

    //-- if str1 before str2 GUI CHECK--//
    private boolean isBefore (String str1 , String str2){
        //-- Trims --//
        char from,to;
        int day1=0,day2=0;
        int month1=0,month2=0;
        int year1=0,year2=0;
        String tempDate="";

        for(int j=0 ,  i = 0 ;i < str1.length() ; i ++ ) {
            from = str1.charAt(i);
            if (from != '-')
                tempDate += from;
            if (from == '-') {
                j++;
                if (j == 1) {
                    year1 = Integer.parseInt(tempDate);
                    tempDate="";
                }
                if (j == 2) {
                    month1 = Integer.parseInt(tempDate);
                    tempDate="";
                }
            }
        }
        day1 = Integer.parseInt(tempDate);
        tempDate="";

        for(int j=0 ,  i = 0 ;i < str2.length() ; i ++ ) {
            to = str2.charAt(i);
            if (to != '-')
                tempDate += to;
            if (to == '-') {
                j++;
                if (j == 1) {
                    year2 = Integer.parseInt(tempDate);
                    tempDate="";
                }
                if (j == 2) {
                    month2 = Integer.parseInt(tempDate);
                    tempDate="";
                }
            }
        }
        day2 = Integer.parseInt(tempDate);
        tempDate="";
        //--Check is Before --//
        if (year1 < year2)
            return true;
        else if ( year1 == year2 && month1 < month2)
            return true;
        else if ( year1 == year2 && month1 == month2 && day1 <= day2)
            return true;
        else
            return false;
    }
    //-- if str1 <= str3 <= str2 --//
    private boolean isBefore(String str1,String str2,String str3){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date firstDate = null;
        try {
            firstDate = sdf.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date secondDate = null;
        try {
            secondDate = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date thirdDate = null;
        try {
            thirdDate = sdf.parse(str3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int first = firstDate.compareTo(thirdDate);
        int second = secondDate.compareTo(thirdDate);
        if ((first == 0 || first < 0) && (second == 0 || second >0))
            return true;
        return false;
    }
    private void DatePickerEdit(int selectedActivation){
        String dateStr="";
        for(int j=0 ,  i = 0 ;i < activationSipController.getSipActivation().get(selectedActivation).getDatePicker().length() ; i ++ ) {
            char a = activationSipController.getSipActivation().get(selectedActivation).getDatePicker().charAt(i);
            if (a != '-')
                dateStr += a;
            if (a == '-') {
                j++;
                if (j == 1) {
                    activationFormSIP.datePicker.getModel().setYear(Integer.parseInt(dateStr));
                    dateStr="";
                }
                if (j == 2) {
                    activationFormSIP.datePicker.getModel().setMonth(Integer.parseInt(dateStr)-1);
                    dateStr="";
                }
            }
        }
        activationFormSIP.datePicker.getModel().setDay(Integer.parseInt(dateStr));
        activationFormSIP.datePicker.getModel().setSelected(true);
    }

    public void setDataFromSipListener(GetDataFromSipListener listener) {
        this.getDataFromSipListener = listener;
    }

    private void loadActivationToJlist(){
        try {
            activationSipController.connect();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            activationSipController.loadActivationSipToList();
            activationSipController.getSipActivation();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        activationSipController.disconnect();
        int i = 0,flag=0;
        actModel = new DefaultListModel();
        actModel.removeAllElements();
        //-- Date --//
        //--From--//
        try {
            dateLabelFormatterFrom.valueToString((Date) fromDate.getModel().getValue());
            datePickerEvFrom = dateLabelFormatterFrom.valueToString((Date) fromDate.getModel().getValue());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        //--To--//
        try {
            dateLabelFormatterTo.valueToString((Date) toDate.getModel().getValue());
            datePickerEvTo = dateLabelFormatterTo.valueToString((Date) toDate.getModel().getValue());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        String from = datePickerEvFrom;
        String to = datePickerEvTo;
        if (!from.isEmpty() && !to.isEmpty()) {
            activationsIdArrayList.clear();
            if (isBefore(from, to)) {
                for (i = 0; i < activationSipController.getSipActivation().size(); i++) {
                    DateChoose = activationSipController.getSipActivation().get(i).getDatePicker();
                    if (isBefore(from,to,DateChoose )) {
                        actModel.addElement(activationSipController.getSipActivation().get(i).getCustomerName());
                        activationsIdArrayList.add(activationSipController.getSipActivation().get(i).getId());
                        activations.setModel(actModel);
                        flag =1 ;
                    }
                }
            } else
                JOptionPane.showMessageDialog(ListOfActivationView.this, "זמן ההתחלה גדול מזמן הסיום", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(ListOfActivationView.this, "אנא הכנס תאריכים", "Error", JOptionPane.ERROR_MESSAGE);

        }
        //-- if flag==0 then the List is Empty --//
        if (flag==0) {
            actModel.removeAllElements();
            activations.setModel(actModel);

        }
    }
}
