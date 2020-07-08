package Controller;

import Model.*;
import View.ActivationsMoves;
import View.FormEvent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Controller {
    DataBase db = new DataBase();

    public List<ActivationFormSip> getSipActivation() {
        return db.getActivationSipFromList();
    }

    public Users getLoggedUser(){
        return db.getLoggedUser();
    }

    public Connection getConnection() {
        return db.getCon();
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
        String expertName = ev.getFirstName();
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
        String firstNAme = ev.getFirstName();
        String connectionType = ev.getConnectionType();
        String projectManagerFirstName= ev.getProjectManagerFirstName();
        String activationType;
        activationType = ActivationType.Sip.toString();
        int id = ActivationsMoves.FormId.getActivationId();
        int numOfFail = ev.getNumofFails();
        String status = ev.getStatus();
        String lastUpate = ev.getLastUpdate();


        ActivationFormSip activationFormSip= new ActivationFormSip(id,customerID, customerName,contactName,customerPhoneNumber,customerEmail,customerTechName,customerTechPhoneNumber,pbxType,typeOfCalls,identificationType,totalNumbers,snbNumber,
                areaCode,emergencyCity,callOutSideCountry,crNumber,trunkNumber,datePicker,wanAddress,lanAddress,ipAddress,internetUser,infrastructure,routerType,CODEC,totalCalls,signalAddress,mediaAddress,sbcPort,firstNAme,connectionType,projectManagerFirstName,activationType,status,numOfFail,lastUpate);
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

    public void loadCalenderSipActivationToList() throws SQLException {
        db.loadCalenderSipActivationToList();
    }

    public void loadNumberRangeFromDataBaseToList(int activation_id) throws SQLException {
        db.loadNumberRangeFromDataBaseToList(activation_id);
    }

    public void disconnect(){
        //db.disconnect();
    }
    public void connect () throws Exception {
        db.connect();
    }

    //-- Number Range to DataBse --//
    public void insertingNumberRangeToDataBase() throws SQLException {
        db.insertingNumberRangeToDataBase(ActivationsMoves.SessionId.getNewID());
    }
    public List<NumberRanges> getNumberRanges(){
        return db.getNumberRanges();
    }
    public void updateNumberRangeToDataBase(int activation_id) throws SQLException {
        db.updateNumberRangeToDataBase(activation_id);
    }

    public void failActivation(int activationId) throws SQLException {
        db.failActivation(activationId);
    }

    public void getNumOfFails(int id){
        db.getNumOfFails(id);
    }

    public void clearNumberRange(){
        db.clearNumberRange();
    }

}