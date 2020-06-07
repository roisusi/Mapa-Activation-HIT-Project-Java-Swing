package Controller;

import Model.ActivationFormSip;
import Model.DataBase;
import Model.Login;
import Model.Users;
import View.FormEvent;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    DataBase db = new DataBase();
    Login login;

    public List<Login> getUsers() {
        return db.getLoginUsersFromList();
    }
    public List<ActivationFormSip> getSipActivation() {
        return db.getActivationSipFromList();
    }
    public List<Users>getUsername(){
        return db.getUserNamesFromList();
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
        String numberRange = ev.getNumberRange();
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

        ActivationFormSip activationFormSip= new ActivationFormSip(customerID, customerName,contactName,customerPhoneNumber,customerEmail,customerTechName,customerTechPhoneNumber,pbxType,typeOfCalls,identificationType,totalNumbers,snbNumber,
            numberRange,areaCode,emergencyCity,callOutSideCountry,crNumber,trunkNumber,datePicker,wanAddress,lanAddress,ipAddress,internetUser,infrastructure,routerType,CODEC,totalCalls,signalAddress,mediaAddress,sbcPort,firstNAme);
        db.addActivationSipToList(activationFormSip);
    }
    public void addFirstNameToActivationList(int row, String firstName){
        db.addFirstNameToActivationList(row, firstName);
    }

    public void updateUserExpertFirstName(int row , String firstName) throws SQLException {
        db.updateUserExpertFirstName(row, firstName);
    }

    public void removeActivation(int row)
    {
        db.removeActivationFromList(row);
    }

    public void insertingActivationSipToDataBase() throws SQLException {
        db.insertingActivationSipToDataBase();
    }

    public void loadUsersFromDataBaseToList() throws SQLException {
        db.loadUsersFromDataBaseToList();
    }
    public void loadCalenderSipActivationToList() throws SQLException {
        db.loadCalenderSipActivationToList();
    }

    public void disconnect(){
        db.disconnect();
    }
    public void connect () throws Exception {
        db.connect();
    }
}