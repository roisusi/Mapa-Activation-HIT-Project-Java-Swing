package View;

import Model.ActivationFormSip;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import javax.swing.*;
import java.awt.*;
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
    private JPanel formPanelDown;

    public ListOfActivationView(JFrame parent, List<ActivationFormSip> activationFormSips) {
        activations = new JList();
        panel = new JPanel();
        formPanelUp = new JPanel();
        formPanelDown = new JPanel();

        // New format to Date From//
        UtilDateModel modelFrom = new UtilDateModel();
        fromDate = new JDatePicker(modelFrom);
        String datePickerEvFrom="";
        DateLabelFormatter dateLabelFormatterFrom = new DateLabelFormatter();

        // New format to Date to//
        UtilDateModel modelTo = new UtilDateModel();
        toDate = new JDatePicker(modelTo);
        toDate = new JDatePicker(modelTo);
        String datePickerEvTo="";
        DateLabelFormatter dateLabelFormatterTo = new DateLabelFormatter();



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
            dateLabelFormatterTo.valueToString((Date)fromDate.getModel().getValue());
            datePickerEvTo = dateLabelFormatterTo.valueToString((Date)fromDate.getModel().getValue());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }


        //inorder to set data we need to configure model list
        DefaultListModel actModel = new DefaultListModel();
        int i=0;
        for (i=0 ; i < activationFormSips.size() ; i++) {
            actModel.addElement(activationFormSips.get(i).getCustomerName());
        }


        activations.setModel(actModel);
        //activations.setPreferredSize(new Dimension(400,30)); // set size of of not it fixed size
        activations.setBorder(BorderFactory.createEtchedBorder()); // create just frame border
        activations.setSelectedIndex(1);//set default index selected as 1 allways

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(activations);
        activations.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setPreferredSize(new Dimension(400,400));


        activations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    ActivationFormSIP activationFormSIP = new ActivationFormSIP(panel,1);
                    int selectedActivation = activations.getSelectedIndex();
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

                    String date = activationFormSips.get(selectedActivation).getDatePicker();
                    System.out.println(date);

                    //-- DATE --//
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

        formPanelDown.setLayout(new GridBagLayout());
        GridBagConstraints gcDown = new GridBagConstraints();
        gcDown.fill = GridBagConstraints.NONE;

        int top = 50;

        //-- Up Rows --//
        gcUp.weighty=1;
        gcUp.weightx=1;

        gcUp.gridy = 0;
        gcUp.gridx = 0;
        gcUp.insets = new Insets(top,0,0,0);
        gcUp.anchor = GridBagConstraints.LINE_END;
        formPanelUp.add(new JLabel("תאריך התחלה"),gcUp);
        gcUp.gridx++;
        gcUp.insets = new Insets(top,0,0,0);
        gcUp.anchor = GridBagConstraints.LINE_END;
        formPanelUp.add(fromDate,gcUp);
        gcUp.gridx++;
        gcUp.insets = new Insets(top,0,0,0);
        gcUp.anchor = GridBagConstraints.LINE_END;
        formPanelUp.add(new JLabel("תאריך סיום"),gcUp);
        gcUp.gridx++;
        gcUp.insets = new Insets(top,0,0,0);
        gcUp.anchor = GridBagConstraints.LINE_START;
        formPanelUp.add(toDate,gcUp);

        //-- Down Rows --//
        gcDown.weighty=1;
        gcDown.weightx=1;
        gcDown.gridy = 0;
        gcDown.gridx = 0;
        gcDown.insets = new Insets(100,0,0,0);
        gcDown.anchor = GridBagConstraints.CENTER;
        formPanelDown.add(scrollPane,gcDown);


        // Add sub panels //
        setLayout(new BorderLayout());
        add(formPanelUp,BorderLayout.NORTH);
        add(formPanelDown,BorderLayout.CENTER);


        setModal(true);
        setSize(600, 500); // Size the Frame
        setLocationRelativeTo(parent);

    }


}
