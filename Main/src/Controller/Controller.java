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
        return db.getUsers();
    }
    public List<ActivationFormSip> getSipActivation() {
        return db.getSipActivation();
    }
    public List<Users>getUsername(){
        return db.getUserNames();
    }
    public List<Users> SingleUser(String name) throws SQLException {
        return db.getSingleUser(name) ;
    }
    public void addActivationSip(FormEvent ev)
    {
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
        db.addActivationSip(activationFormSip);
    }
    public void removeActivation(int row)
    {
        db.removeActivation(row);
    }
    public void updateExpertUserName(int row,String firstName) throws SQLException {
        db.updateUserExpertFirstName(row, firstName);
    }
    public void save() throws SQLException {
        db.saveActivaionSip();
    }
    public void loadUsers() throws SQLException {
        db.loadUsers();
    }
    public void loadTheActivationSip() throws SQLException {
        db.loadCalenderSipActivation();
    }
    public void disconnect(){
        db.disconnect();
    }
    public void connect () throws Exception {
        db.connect();
    }
}