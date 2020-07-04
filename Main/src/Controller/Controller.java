package Controller;

import Model.*;
import View.ActivationsMoves;
import View.FormEvent;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Controller {
    DataBase db = new DataBase();

    public List<Login> getUsers() {
        return db.getLoginUsersFromList();
    }
    public List<ActivationFormSip> getSipActivation() {
        return db.getActivationSipFromList();
    }
    public List<Users> getSystemUsers() { return db.getUsersFromList(); }
    public Users getUserFirstNameLogged(){
        return db.getUserFirstNameLogged();
    }
    public Login getLoginUser() { return db.getLoginUser(); }
    public List<NumberRanges> getActivationSipFromList(){
        return db.getNumberRanges();
    }

    public boolean isUserAlreadyExists(Users user) {
        return db.isUserAlreadyExists(user);
    }
    public boolean isLoginUserAlreadyExists(Login login) {
        return db.isLoginUserAlreadyExists(login);
    }

    public boolean loginUserAuthentication(String username, String password) throws SQLException {
        return db.loginUserAuthentication(username, password);
    };

    public void addNumberRange(FormEvent ev){
        ArrayList from = ev.getFrom();
        ArrayList to = ev.getTo();
        String trunkNumber = ev.getTrunkNumber();

        NumberRanges numberRanges = new NumberRanges(from,to,trunkNumber);
        db.addNumberRangeToList(numberRanges);

    }
    public void addSystemUser(Users user) {
        db.addUserToList(user);
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
        String connectionType = ev.getConnectionType();
        String projectManagerFirstName= ev.getProjectManagerFirstName();
        String activationType;
        activationType = ActivationType.Sip.toString();

        ActivationFormSip activationFormSip= new ActivationFormSip(customerID, customerName,contactName,customerPhoneNumber,customerEmail,customerTechName,customerTechPhoneNumber,pbxType,typeOfCalls,identificationType,totalNumbers,snbNumber,
            numberRange,areaCode,emergencyCity,callOutSideCountry,crNumber,trunkNumber,datePicker,wanAddress,lanAddress,ipAddress,internetUser,infrastructure,routerType,CODEC,totalCalls,signalAddress,mediaAddress,sbcPort,firstNAme,connectionType,projectManagerFirstName,activationType,"לא");
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
    public void updateSystemUser(Object obj, int row, int column) throws SQLException {
        db.updateSystemUser(obj, row, column);
    }
    public void updateLoginUser(Object obj, int row, int column) throws SQLException {
        db.updateLoginUser(obj, row, column);
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
        String connectionType = ev.getConnectionType();
        String projectManagerFirstName= ev.getProjectManagerFirstName();
        String activationType;
        activationType = ActivationType.Sip.toString();
        int id = ActivationsMoves.FormId.getActivationId();


        ActivationFormSip activationFormSip= new ActivationFormSip(id,customerID, customerName,contactName,customerPhoneNumber,customerEmail,customerTechName,customerTechPhoneNumber,pbxType,typeOfCalls,identificationType,totalNumbers,snbNumber,
                numberRange,areaCode,emergencyCity,callOutSideCountry,crNumber,trunkNumber,datePicker,wanAddress,lanAddress,ipAddress,internetUser,infrastructure,routerType,CODEC,totalCalls,signalAddress,mediaAddress,sbcPort,firstNAme,connectionType,projectManagerFirstName,activationType,"לא");
        db.updateActivationSipToList(activationFormSip);

    }
    public void updateActivationSipToDataBase(int id) throws SQLException {
        db.updateActivationSipToDataBase(id);
    }

    public void removeActivation(int row)
    {
        db.removeActivationFromList(row);
    }
    public void removeUser(int row)
    {
        db.removeUserFromList(row);
    }
    public void insertingActivationSipToDataBase() throws SQLException {
        db.insertingActivationSipToDataBase();
    }
    public void insertingUserToDataBase(Users user, int id) throws SQLException {
        db.insertingUserToDataBase(user, id);
    }
    public void insertingLoginUserToDataBase(Login login) throws SQLException {
        db.insertingLoginUserToDataBase(login);
    }

    public void loadLoggedUser(int id) throws SQLException {
        db.loadLoggedUser(id);
    }
    public void loadUsersFromDataBaseToList() throws SQLException {
        db.loadUsersFromDataBaseToList();
    }
    public void loadSystemUsersFromDataBaseToList() throws SQLException {
        db.loadSystemUsersFromDataBaseToList();
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

    //-- Number Range to DataBse --//
    public void insertingNumberRangeToDataBase() throws SQLException {
        db.insertingNumberRangeToDataBase(ActivationsMoves.SessionId.getNewID());
    }

}