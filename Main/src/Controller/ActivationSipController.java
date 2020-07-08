package Controller;

import Model.*;
import View.ActivationsMoves;
import View.FormEvent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ActivationSipController {
    DataBase db = new DataBase();

    public List<ActivationFormSip> getSipActivation() {
        return db.getActivationSipFromList();
    }

    public void addActivationSip(FormEvent ev) {
        String customerID = ev.getCustomerID();
        String customerName = ev.getCustomerName();
        String contactName = ev.getContactName();
        String customerPhoneNumber = ev.getCustomerPhoneNumber();
        String customerEmail = ev.getCustomerEmail();
        String customerTechName = ev.getCustomerTechName();
        String customerTechPhoneNumber = ev.getCustomerTechPhoneNumber();
        String pbxType = ev.getPbxType();
        String typeOfCalls = ev.getTypeOfCalls();
        String identificationType = ev.getIdentificationType();
        int totalNumbers = ev.getTotalNumbers();
        String snbNumber = ev.getSnbNumber();
        String areaCode = ev.getAreaCode();
        String emergencyCity = ev.getEmergencyCity();
        String callOutSideCountry = ev.getCallOutSideCountry();
        String crNumber = ev.getCrNumber();
        String trunkNumber = ev.getTrunkNumber();
        String datePicker = ev.getDatePicker();
        String wanAddress = ev.getWanAddress();
        String lanAddress = ev.getLanAddress();
        String ipAddress = ev.getIpAddress();
        String internetUser = ev.getInternetUser();
        String infrastructure = ev.getInfrastructure();
        String routerType = ev.getRouterType();
        String CODEC = ev.getCODEC();
        int totalCalls = ev.getTotalCalls();
        String signalAddress = ev.getSignalAddress();
        String mediaAddress = ev.getMediaAddress();
        int sbcPort = ev.getSbcPort();
        String expertName = ev.getExpertName();
        String connectionType = ev.getConnectionType();
        String projectManagerFirstName= ev.getProjectManagerFirstName();
        String activationType;
        activationType = ActivationType.Sip.toString();
        int activationFailCounter = ev.getNumofFails();
        String status = ev.getStatus();
        String lastUpdate = ev.getLastUpdate();

        ActivationFormSip activationFormSip= new ActivationFormSip(customerID, customerName,contactName,customerPhoneNumber,customerEmail,customerTechName,customerTechPhoneNumber,pbxType,typeOfCalls,identificationType,totalNumbers,snbNumber,
            areaCode,emergencyCity,callOutSideCountry,crNumber,trunkNumber,datePicker,wanAddress,lanAddress,ipAddress,internetUser,infrastructure,routerType,CODEC,totalCalls,signalAddress,mediaAddress,sbcPort,expertName,connectionType,projectManagerFirstName,activationType,status,activationFailCounter,lastUpdate);
        db.addActivationSipToList(activationFormSip);
    }
    public void addFirstNameToActivationList(int row, String firstName){
        db.addFirstNameToActivationList(row, firstName);
    }
    public void addStatusToActivationList(String status, int row){
        db.addFirstNameToActivationList(status, row);
    }

    public void updateUserExpertFirstName(int row , String firstName) throws SQLException {
        db.updateUserExpertFirstName(row, firstName);
    }
    public void updateStatus(String status,int row) throws SQLException {
        db.updateStatus(status,row);
    }

    public void updateActivationSip(FormEvent ev){
        String customerID = ev.getCustomerID();
        String customerName = ev.getCustomerName();
        String contactName = ev.getContactName();
        String customerPhoneNumber = ev.getCustomerPhoneNumber();
        String customerEmail = ev.getCustomerEmail();
        String customerTechName = ev.getCustomerTechName();
        String customerTechPhoneNumber = ev.getCustomerTechPhoneNumber();
        String pbxType = ev.getPbxType();
        String typeOfCalls = ev.getTypeOfCalls();
        String identificationType = ev.getIdentificationType();
        int totalNumbers = ev.getTotalNumbers();
        String snbNumber = ev.getSnbNumber();
        String areaCode = ev.getAreaCode();
        String emergencyCity = ev.getEmergencyCity();
        String callOutSideCountry = ev.getCallOutSideCountry();
        String crNumber = ev.getCrNumber();
        String trunkNumber = ev.getTrunkNumber();
        String datePicker = ev.getDatePicker();
        String wanAddress = ev.getWanAddress();
        String lanAddress = ev.getLanAddress();
        String ipAddress = ev.getIpAddress();
        String internetUser = ev.getInternetUser();
        String infrastructure = ev.getInfrastructure();
        String routerType = ev.getRouterType();
        String CODEC = ev.getCODEC();
        int totalCalls = ev.getTotalCalls();
        String signalAddress = ev.getSignalAddress();
        String mediaAddress = ev.getMediaAddress();
        int sbcPort = ev.getSbcPort();
        String expertFirstName = ev.getExpertName();
        String connectionType = ev.getConnectionType();
        String projectManagerFirstName= ev.getProjectManagerFirstName();
        String activationType;
        activationType = ActivationType.Sip.toString();
        int id = ActivationsMoves.FormId.getActivationId();
        int numOfFail = ev.getNumofFails();
        String status = ev.getStatus();
        String lastUpdate = ev.getLastUpdate();


        ActivationFormSip activationFormSip= new ActivationFormSip(id,customerID, customerName,contactName,customerPhoneNumber,customerEmail,customerTechName,customerTechPhoneNumber,pbxType,typeOfCalls,identificationType,totalNumbers,snbNumber,
                areaCode,emergencyCity,callOutSideCountry,crNumber,trunkNumber,datePicker,wanAddress,lanAddress,ipAddress,internetUser,infrastructure,routerType,CODEC,totalCalls,signalAddress,mediaAddress,sbcPort,expertFirstName,connectionType,projectManagerFirstName,activationType,status,numOfFail,lastUpdate);
        db.updateActivationSipToList(activationFormSip);

    }
    public void updateActivationSipToDataBase(int id) throws SQLException {
        db.updateActivationSipToDataBase(id);
    }

    public void removeActivation(int row)
    {
        db.removeActivationFromList(row);
    }

    public void insertingActivationSipToDataBase() throws SQLException {
        db.insertingActivationSipToDataBase();
    }

    public void loadActivationSipToList() throws SQLException {
        db.loadActivationSipToList();
    }
    public void disconnect(){
        //db.disconnect();
    }
    public void connect () throws Exception {
        db.connect();
    }

    public void failActivation(int activationId) throws SQLException {
        db.failActivation(activationId);
    }

    public Connection getConnection() {
        return db.getCon();
    }

    public String getExpertName(int id){
        return db.getExpertName(id);
    }

    public boolean checkIP(int a , int b , int c , int d){
        if ((a >= 0 && a <= 255) && b >= 0 && b <= 255 && c >= 0 && c <= 255 && d >= 0 && d <= 255) {
            if ((a == 10 && b != 142) || (a == 192 && b == 168) || (a == 172 && (b >= 16 && b <= 31)) || a == 127) {
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }
}