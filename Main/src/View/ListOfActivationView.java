package View;

import Model.ActivationFormSip;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import javax.print.event.PrintJobAttributeListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;
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
    private List<ActivationFormSip> activationFormSips;
    private ActivationFormSIP activationFormSIP;
    private String datePickerEvFrom;
    private String datePickerEvTo;
    private JLabel expertName;
    private JLabel projectManagerName;
    private JLabel numberOfSuccess;

    public ListOfActivationView(JFrame parent, List<ActivationFormSip> activationFormSips) {
        activations = new JList();
        panel = new JPanel();
        formPanelUp = new JPanel();
        formPanelDownLeft = new JPanel();
        formPanelDownRight = new JPanel();
        view = new JButton("הצג");
        this.activationFormSips = activationFormSips;
        activationFormSIP = new ActivationFormSIP(panel,1);
        expertName = new JLabel();
        projectManagerName = new JLabel();

        // New format to Date From//
        UtilDateModel modelFrom = new UtilDateModel();
        fromDate = new JDatePicker(modelFrom);
        datePickerEvFrom="";
        DateLabelFormatter dateLabelFormatterFrom = new DateLabelFormatter();

        // New format to Date to//
        UtilDateModel modelTo = new UtilDateModel();
        toDate = new JDatePicker(modelTo);
        datePickerEvTo="";
        DateLabelFormatter dateLabelFormatterTo = new DateLabelFormatter();

        //-- View Get List from Dates --//
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //-- Date --//
                //--From--//
                try {
                    dateLabelFormatterFrom.valueToString((Date)fromDate.getModel().getValue());
                    datePickerEvFrom = dateLabelFormatterFrom.valueToString((Date)fromDate.getModel().getValue());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                //--To--//
                try {
                    dateLabelFormatterTo.valueToString((Date)toDate.getModel().getValue());
                    datePickerEvTo = dateLabelFormatterTo.valueToString((Date)toDate.getModel().getValue());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                String from =datePickerEvFrom;
                String to =datePickerEvTo;
                DefaultListModel actModel = new DefaultListModel();
                int i=0;
                if (isBefore(from,to)){
                    for (i=0 ; i < activationFormSips.size() ; i++) {
                        DateChoose = activationFormSips.get(i).getDatePicker();
                        if (isBefore(DateChoose, from) || (isBefore(DateChoose, to))) {
                            actModel.addElement(activationFormSips.get(i).getCustomerName());
                            activations.setModel(actModel);
                        }
                    }
                }
                else
                    JOptionPane.showMessageDialog(ListOfActivationView.this, "זמן ההתחלה גדול מזמן הסיום", "Error", JOptionPane.ERROR_MESSAGE);
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
                int selectedActivation = activations.getSelectedIndex();

                if (e.getButton() == MouseEvent.BUTTON1) //Click Once
                {
                    expertName.setText(activationFormSips.get(selectedActivation).getFirstName());
                    expertName.setForeground(Color.BLUE);
                    projectManagerName.setText(activationFormSips.get(selectedActivation).getProjectManagerFirstName());
                    projectManagerName.setForeground(Color.BLUE);
                    //numberOfSuccess
                }
                if(e.getClickCount() == 2) //Double click
                {
                    activationFormSIP.customerID.setText(activationFormSips.get(selectedActivation).getCustomerID());
                    activationFormSIP.customerName.setText(activationFormSips.get(selectedActivation).getCustomerName());
                    activationFormSIP.contactName.setText(activationFormSips.get(selectedActivation).getContactName());
                    activationFormSIP.customerPhoneNumber.setText(activationFormSips.get(selectedActivation).getCustomerPhoneNumber());
                    activationFormSIP.customerEmail.setText(activationFormSips.get(selectedActivation).getCustomerEmail());
                    activationFormSIP.customerTechName.setText(activationFormSips.get(selectedActivation).getCustomerTechName());
                    activationFormSIP.customerTechPhoneNumber.setText(activationFormSips.get(selectedActivation).getCustomerTechPhoneNumber());
                    activationFormSIP.pbxType.setText(activationFormSips.get(selectedActivation).getPbxType());
                    activationFormSIP.infrastructure.setText(activationFormSips.get(selectedActivation).getInfrastructure());
                    for (int i=0 ; i <  activationFormSIP.connectionType.getItemCount();i++){
                        String connectionType = (String)activationFormSIP.connectionType.getModel().getElementAt(i);
                        String actConnectioType = activationFormSips.get(selectedActivation).getConnectionType();
                        if (actConnectioType.equals(connectionType))
                            activationFormSIP.connectionType.setSelectedIndex(i);
                    }
                    activationFormSIP.totalNumbers.setText(Integer.toString(activationFormSips.get(selectedActivation).getTotalNumbers()));
                    activationFormSIP.totalCalls.setText(Integer.toString(activationFormSips.get(selectedActivation).getTotalCalls()));

                    String routerType = activationFormSips.get(selectedActivation).getRouterType();
                    if (routerType.isEmpty())
                        activationFormSIP.routerTypeGroup.setSelected(activationFormSIP.routerTypeNO.getModel(),true);
                    else
                    {
                        activationFormSIP.routerTypeGroup.setSelected(activationFormSIP.routerTypeYES.getModel(),true);
                        activationFormSIP.routerTypeTextField.setText(activationFormSips.get(selectedActivation).getRouterType());
                        activationFormSIP.routerTypeTextField.setEnabled(true);
                    }

                    activationFormSIP.routerTypeTextField.getText();

                    for (int i=0 ; i <  activationFormSIP.CODEC.getItemCount();i++){
                        String CODEC = (String)activationFormSIP.CODEC.getModel().getElementAt(i);
                        String actCODEC = activationFormSips.get(selectedActivation).getCODEC();
                        if (CODEC.equals(actCODEC))
                            activationFormSIP.CODEC.setSelectedIndex(i);
                    }

                    for (int i=0 ; i <  activationFormSIP.typeOfCalls.getItemCount();i++){
                        String typeOfCalls = (String)activationFormSIP.typeOfCalls.getModel().getElementAt(i);
                        String actTypeOfCalls = activationFormSips.get(selectedActivation).getTypeOfCalls();
                        if (typeOfCalls.equals(actTypeOfCalls))
                            activationFormSIP.typeOfCalls.setSelectedIndex(i);
                    }

                    for (int i=0 ; i <  activationFormSIP.identificationType.getItemCount();i++){
                        String identificationType = (String)activationFormSIP.identificationType.getModel().getElementAt(i);
                        String actIdentificationType = activationFormSips.get(selectedActivation).getIdentificationType();
                        if (identificationType.equals(actIdentificationType))
                            activationFormSIP.identificationType.setSelectedIndex(i);
                    }
                    activationFormSIP.snbNumber.setText(activationFormSips.get(selectedActivation).getSnbNumber());

                    //--WAN--//
                    String wan="";
                    for(int j=0 ,  i = 0 ;i < activationFormSips.get(selectedActivation).getWanAddress().length() ; i ++ ) {
                        char a = activationFormSips.get(selectedActivation).getWanAddress().charAt(i);

                        if (a != '.')
                            wan += a;
                        if (a == '.') {
                            j++;
                            if (j == 1) {
                                activationFormSIP.wanAddressA.setText(wan);
                                wan="";
                            }
                            if (j == 2){
                                activationFormSIP.wanAddressB.setText(wan);
                                wan="";
                            }
                            if (j == 3){
                                activationFormSIP.wanAddressC.setText(wan);
                                wan="";
                            }
                        }
                            activationFormSIP.wanAddressD.setText(wan);
                    }

                    //--LAN--//
                    String lan="";
                    for(int j=0 ,  i = 0 ;i < activationFormSips.get(selectedActivation).getLanAddress().length() ; i ++ ) {
                        char a = activationFormSips.get(selectedActivation).getLanAddress().charAt(i);

                        if (a != '.')
                            lan += a;
                        if (a == '.') {
                            j++;
                            if (j == 1) {
                                activationFormSIP.lanAddressA.setText(lan);
                                lan="";
                            }
                            if (j == 2){
                                activationFormSIP.lanAddressB.setText(lan);
                                lan="";
                            }
                            if (j == 3){
                                activationFormSIP.lanAddressC.setText(lan);
                                lan="";
                            }
                        }
                        activationFormSIP.lanAddressD.setText(lan);
                    }

                    //--IP--//
                    String ip="";
                    for(int j=0 ,  i = 0 ;i < activationFormSips.get(selectedActivation).getIpAddress().length() ; i ++ ) {
                        char a = activationFormSips.get(selectedActivation).getIpAddress().charAt(i);

                        if (a != '.')
                            ip += a;
                        if (a == '.') {
                            j++;
                            if (j == 1) {
                                activationFormSIP.ipAddressA.setText(ip);
                                ip="";
                            }
                            if (j == 2){
                                activationFormSIP.ipAddressB.setText(ip);
                                ip="";
                            }
                            if (j == 3){
                                activationFormSIP.ipAddressC.setText(ip);
                                ip="";
                            }
                        }
                        activationFormSIP.ipAddressD.setText(ip);
                    }
                    activationFormSIP.internetUser.setText(activationFormSips.get(selectedActivation).getInternetUser());

                    for (int i=0 ; i <  activationFormSIP.signalAddress.getItemCount();i++){
                        String signalAddress = (String)activationFormSIP.signalAddress.getModel().getElementAt(i);
                        String actSignalAddress = activationFormSips.get(selectedActivation).getSignalAddress();
                        if (signalAddress.equals(actSignalAddress))
                            activationFormSIP.signalAddress.setSelectedIndex(i);
                    }

                    for (int i=0 ; i <  activationFormSIP.mediaAddress.getItemCount();i++){
                        String mediaAddress = (String)activationFormSIP.mediaAddress.getModel().getElementAt(i);
                        String actMediaAddress = activationFormSips.get(selectedActivation).getMediaAddress();
                        if (mediaAddress.equals(actMediaAddress))
                            activationFormSIP.mediaAddress.setSelectedIndex(i);
                    }

                    for (int i=0 ; i <  activationFormSIP.areaCode.getItemCount();i++){
                        String areaCode = (String)activationFormSIP.areaCode.getModel().getElementAt(i);
                        String actAreaCode = activationFormSips.get(selectedActivation).getAreaCode();
                        if (areaCode.equals(actAreaCode))
                            activationFormSIP.areaCode.setSelectedIndex(i);
                    }

                    activationFormSIP.emergencyCity.setText(activationFormSips.get(selectedActivation).getEmergencyCity());

                    //-- DATE --//
                    DatePickerEdit(selectedActivation);

                    String callOutSideCountry = activationFormSips.get(selectedActivation).getCallOutSideCountry();
                    if (callOutSideCountry.equals("לא"))
                        activationFormSIP.callOutSideCountry.setSelected(activationFormSIP.callOutSideCountryNO.getModel(),true);
                    else
                        activationFormSIP.callOutSideCountry.setSelected(activationFormSIP.callOutSideCountryYES.getModel(),true);

                    activationFormSIP.crNumber.setText(activationFormSips.get(selectedActivation).getCrNumber());
                    activationFormSIP.trunkNumber.setText(activationFormSips.get(selectedActivation).getTrunkNumber());
                    activationFormSIP.sbcPort.setValue(activationFormSips.get(selectedActivation).getSbcPort());

                    activationFormSIP.setVisible(true);
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

    //-- if str1 before str2 --//
    private boolean isBefore(String str1,String str2){

        //-- Trims --//
        char from,to;
        int day1=0;
        int day2=0;
        int month1=0;
        int month2=0;
        int year1=0;
        int year2=0;
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
        if (year1 > year2)
            return false;
        else if (year1 == year2 && month1 > month2 )
            return false;
            else if (year1 == year2 && month1 == month2 && day1 > day2)
                return false;
                else
                    return true;
    }

    private void DatePickerEdit(int selectedActivation){
        String dateStr="";
        for(int j=0 ,  i = 0 ;i < activationFormSips.get(selectedActivation).getDatePicker().length() ; i ++ ) {
            char a = activationFormSips.get(selectedActivation).getDatePicker().charAt(i);
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

}
