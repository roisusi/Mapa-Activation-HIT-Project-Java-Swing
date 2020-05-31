package Controller;

import Model.ActivationFormSip;
import Model.DataBase;
import Model.Login;
import View.ActivationFormSIP;
import View.FormEvent;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Contorller {
    DataBase db = new DataBase();
    Login login;

    public List<Login> getUsers() {
        return db.getUsers();
    }
    public List<ActivationFormSip> getSipActivation() {
        return db.getSipActivation();
    }

    public void addActivationSip(FormEvent ev)
    {
        int customerID = ev.getCustomerID();
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
        Date datePicker = ev.getDatePicker();
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

        ActivationFormSip activationFormSip= new ActivationFormSip(customerID, customerName,contactName,customerPhoneNumber,customerEmail,customerTechName,customerTechPhoneNumber,pbxType,typeOfCalls,identificationType,totalNumbers,snbNumber,
            numberRange,areaCode,emergencyCity,callOutSideCountry,crNumber,trunkNumber,datePicker,wanAddress,lanAddress,ipAddress,internetUser,infrastructure,routerType,CODEC,totalCalls,signalAddress,mediaAddress,sbcPort);
        db.addActivationSip(activationFormSip);
    }
/*    public void removePerson(int row)
    {
        db.removePerson(row);
    }*/

/*    public void save() throws SQLException {
        db.save();
    }*/
    public void loadTheUsers() throws SQLException {
        db.loadUsers();
    }
    public void loadTheActivationSip() throws SQLException {
        db.loadCalanderSipActivation();
    }
    public void disconnect(){
        db.disconnect();
    }
    public void connect () throws Exception {
        db.connect();
    }
}